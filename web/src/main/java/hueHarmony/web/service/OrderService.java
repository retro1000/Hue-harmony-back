package hueHarmony.web.service;

import com.stripe.exception.StripeException;
import hueHarmony.web.dto.OrderDto;
import hueHarmony.web.model.*;
import hueHarmony.web.model.enums.PaymentMethod;
import hueHarmony.web.model.enums.PaymentStatus;
import hueHarmony.web.repository.CartItemRepository;
import hueHarmony.web.repository.OrderRepository;
import hueHarmony.web.util.JwtUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final JwtUtil jwtUtil;
    private final CartItemRepository cartItemRepository;
    private final CustomerService customerService;
    private final LinkedCardService linkedCardService;
    private final StripeService stripeService;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createOnlineOrder(OrderDto orderDto) throws StripeException {
        Order order = createOrder(orderDto);

        Customer customer = handleOrderCustomer(orderDto);

        order.setCustomer(customer);

        order.setOnlineOrder(
                OnlineOrder.builder()
                        .billingAddress(orderDto.getBillingAddress())
                        .shippingAddress(orderDto.getShippingAddress())
                        .order(order)
                        .deliveryService()
                        .district()
                        .city()
                        .build()
        );

        String paymentMethodId = null;
        switch (orderDto.getPaymentMethod()){
            case CARD:
                order.setOrderPayments(
                        List.of(
                            handleOrderPayment(
                                order,
                                orderDto.getPaymentMethod(),
                                orderDto.getCustomerId()
                            )
                        )
                );

                paymentMethodId = linkedCardService.handleLinkedCard(orderDto.getPaymentMethodId(), customer);
                break;
            case COD:
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method.");
        }

        orderRepository.save(order);

        if(orderDto.getPaymentMethod() == PaymentMethod.CARD && paymentMethodId!=null && !paymentMethodId.isBlank() && !paymentMethodId.isEmpty()){
            stripeService.createPaymentIntent(
                    (long) order.getOrderPayments().get(0).getPaymentAmount(),
                    "lkr",
                    paymentMethodId,
                    "Card payment for order number " + order.getOrderNo() + ".",
                    new HashMap<>(){{
                        put("paymentNumber", order.getOrderPayments().get(0).getPaymentNo()+"");
                        put("orderNumber", order.getOrderNo()+"");
                    }}
            );
        }
    }

    @Transactional
    public void createPosOrder(OrderDto orderDto){
        Order order = createOrder(orderDto);

        Customer customer = handleOrderCustomer(orderDto, true);

        order.setCustomer(customer);

        switch (orderDto.getPaymentMethod()){
            case POS_CASH:
//                order.setPaymentStatus(PaymentStatus.PAID);
                break;
            case POS_CARD:
//                order.setPaymentStatus(PaymentStatus.PARTIALLY_PAID);
                break;
            default:
                throw new IllegalArgumentException("Invalid payment method.");
        }

        orderRepository.save(order);
    }

    @Transactional
    protected Order createOrder(OrderDto orderDto){
        Order order = Order.builder()
                .orderNote(orderDto.getOrderNote())
                .orderPaymentMethod(orderDto.getPaymentMethod())
                .createdUser(entityManager.getReference(User.class, jwtUtil.extractUserIdWithToken()))
                .build();

        Set<OrderProduct> orderProducts;

        if (orderDto.getProductId() == 0 && !orderDto.getCartItems().isEmpty()) {
            // Case 1: productId is 0 and there are cart items
            orderProducts = orderDto.getCartItems().stream()
                    .map(cartItem -> OrderProduct.builder()
                            .product(entityManager.getReference(Product.class, cartItemRepository.getProductIdByCartItemId((long) cartItem.getCartItemId())))
                            .order(order)
                            .quantity(cartItem.getQuantity())
                            .build())
                    .collect(Collectors.toSet());

            cartItemRepository.deleteAllById(orderDto.getCartItems().stream().map(cartItem -> (long) cartItem.getCartItemId()).toList());
        } else if (orderDto.getProductId() != 0) {
            // Case 2: productId is not 0
            orderProducts = Set.of(OrderProduct.builder()
                    .product(entityManager.getReference(Product.class, orderDto.getProductId()))
                    .order(order)
                    .quantity(orderDto.getQuantity())
                    .build());
        } else {
            // Case 3: Default case, neither condition is satisfied
            throw new IllegalStateException("No items found for create the order.");
        }

        order.getOrderProducts().addAll(orderProducts);

        return order;
    }

    @Transactional
    protected Payment handleOrderPayment(Order order, PaymentMethod paymentMethod, int customerId){
        Payment payment = Payment.builder()
                .paymentStatus(paymentMethod==PaymentMethod.CASH ? PaymentStatus.PAID : PaymentStatus.PENDING)
                .build();
//        order.setOrderPayments(List.of(payment));

        return payment;
    }

    @Transactional
    protected Customer handleOrderCustomer(OrderDto orderDto, boolean isRetailOrder){
        Customer customer;
        if(orderDto.getCustomerId()==0 &&
                !orderDto.getEmailAddress().isEmpty() &&
                !orderDto.getFirstName().isEmpty() &&
                !orderDto.getLastName().isEmpty() &&
                !orderDto.getContactNos().isEmpty()
        ){
            customer = Customer.builder()
                    .firstName(orderDto.getFirstName())
                    .lastName(orderDto.getLastName())
                    .email(orderDto.getEmailAddress())
                    .contactNos(orderDto.getContactNos())
                    .build();

            if(isRetailOrder){
                RetailCustomer.builder()
                        .customer(customer)
                        .user(entityManager.getReference(User.class, jwtUtil.extractUserIdWithToken()))
                        .build();
            }
        }else{
            customer = entityManager.getReference(Customer.class, orderDto.getCustomerId());
        }

        return customer;
    }
}
