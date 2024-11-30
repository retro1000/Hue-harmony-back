package hueHarmony.web.service;

import hueHarmony.web.controller.Product;
import hueHarmony.web.dto.PosOrderDto;
import hueHarmony.web.dto.PosOrderItemDto;
import hueHarmony.web.dto.PosProductDto;
import hueHarmony.web.dto.SummaryResponseDto;
import hueHarmony.web.model.Loyalty;
import hueHarmony.web.model.PosOrder;
import hueHarmony.web.model.PosOrderItem;
import hueHarmony.web.model.enums.OrderStatus;
import hueHarmony.web.repository.LoyaltyRepository;
import hueHarmony.web.repository.PosOrderRepository;
import hueHarmony.web.repository.ProductRepository;
import hueHarmony.web.util.FunctionsUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PosService {

    private final Product product;
    @PersistenceContext
    private EntityManager entityManager;
    private final ProductRepository productRepository;
    private final PosOrderRepository posOrderRepository;
    private final LoyaltyRepository loyaltyRepository;

    public List<PosProductDto> getProducts() {
        return productRepository.getProducts();
    }

    @Transactional
    public PosOrder createOrder(PosOrderDto orderDto) {
        // Map DTO fields to Entity using Builder
        PosOrder order = PosOrder.builder()
               .orderDate(orderDto.getOrderDate())
                //.customerName(orderDto.getCustomerName())
                .phoneNumber(orderDto.getPhoneNumber())
                .total(orderDto.getTotal())
                .subTotal(orderDto.getSubTotal())
                .discount(orderDto.getDiscount())
                .paymentMethod(orderDto.getPaymentMethod())
                .orderStatus(orderDto.getOrderStatus() != null ? orderDto.getOrderStatus() : OrderStatus.COMPLETED)
                .cashierId(orderDto.getCashierId())
                .build();

        // Add logic to handle items (if applicable)
        if (orderDto.getItems() != null && !orderDto.getItems().isEmpty()) {
            List<PosOrderItem> items = orderDto.getItems().stream()
                    .map(itemDto -> PosOrderItem.builder()
                            .product(entityManager.getReference(hueHarmony.web.model.Product.class,itemDto.getProductId()))
                            .order(order)
                            .price(itemDto.getPrice())
                            .quantity(itemDto.getQuantity())
                            .build())
                    .toList();
            order.setItems(items);
        }
        int points = FunctionsUtil.calculateLoyaltyPoints(orderDto.getSubTotal());
        int updatedRows = loyaltyRepository.updateLoyaltyPoints(orderDto.getPhoneNumber(), points);

        if (updatedRows == 0) {
            Loyalty newLoyalty = new Loyalty();
            newLoyalty.setContactNo(orderDto.getPhoneNumber());
            newLoyalty.setLoyaltyPoints(points);
            loyaltyRepository.save(newLoyalty);
        }


        // Save the order to the database (assuming you have a repository)
        return posOrderRepository.save(order);
    }


public SummaryResponseDto getTotalsForCashierOnDate(Long cashierId, LocalDate parsedDate) {
    // Use the parsedDate to get the start and end of the day
    LocalDateTime startOfDay = parsedDate.atStartOfDay();
    LocalDateTime endOfDay = parsedDate.atTime(23, 59, 59);

    // Fetch orders for the cashier on the given date
    List<PosOrder> orders = posOrderRepository.findByCashierAndOrderDateBetween(cashierId, startOfDay, endOfDay);

    // Initialize totals
    BigDecimal total = BigDecimal.ZERO;
    BigDecimal cashTotal = BigDecimal.ZERO;
    BigDecimal cardTotal = BigDecimal.ZERO;
    BigDecimal discountTotal = BigDecimal.ZERO;

    // Loop through orders and calculate totals
    for (PosOrder order : orders) {
        total = total.add(order.getTotal());
        discountTotal = discountTotal.add(order.getDiscount());

        if ("Cash".equalsIgnoreCase(order.getPaymentMethod())) {
            cashTotal = cashTotal.add(order.getTotal());
        } else if ("Card".equalsIgnoreCase(order.getPaymentMethod())) {
            cardTotal = cardTotal.add(order.getTotal());
        }
    }

    // Return the totals in a response object
    return new SummaryResponseDto(total, cashTotal, cardTotal, discountTotal);
}

public List<PosOrder> getCompletedOrdersByCashier(Long cashierId) {
    // Fetch completed orders for the cashier
    return posOrderRepository.findByCashierAndOrderStatus(cashierId, OrderStatus.COMPLETED);
}

public Optional<PosOrder> getOrderById(Long orderId) {
    // Fetch the order by its ID, returns Optional to handle cases where the order might not exist
    return posOrderRepository.findById(orderId);
}
}
