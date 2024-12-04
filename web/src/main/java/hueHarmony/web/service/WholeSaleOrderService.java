package hueHarmony.web.service;

import hueHarmony.web.dto.WholeSaleOrderResponseDto;
import hueHarmony.web.dto.WholeSaleOrderdto;
import hueHarmony.web.model.Product;
import hueHarmony.web.model.SalesOrderProduct;
import hueHarmony.web.model.WholeSaleCustomer;
import hueHarmony.web.model.WholeSaleOrder;
import hueHarmony.web.model.enums.OrderStatus;
import hueHarmony.web.repository.WholeSaleOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WholeSaleOrderService {

    private final WholeSaleOrderRepository wholeSaleOrderRepository;

    private final InvoiceService invoiceService;

    @PersistenceContext
    private EntityManager entityManager;

    public void createOrder(WholeSaleOrderdto wholeSaleOrderDto) {

        WholeSaleOrder wholeSaleOrder = WholeSaleOrder.builder()
                .orderDate(wholeSaleOrderDto.getOrderDate())
                .orderNotes(wholeSaleOrderDto.getOrderNotes())
                .customer(entityManager.getReference(WholeSaleCustomer.class,wholeSaleOrderDto.getCustomer()))
                .discountAmount(wholeSaleOrderDto.getDiscountAmount())
                .billingAddress(wholeSaleOrderDto.getBillingAddress())
                .totalAmount(wholeSaleOrderDto.getTotalAmount())
                .shipmentVariationStatus(wholeSaleOrderDto.getShipmentVariationStatus())
                .shipmentDate(wholeSaleOrderDto.getShipmentDate())
                .build();

        wholeSaleOrder.setOrderItems(wholeSaleOrderDto.getOrderItems().stream().map(orderItemDto -> SalesOrderProduct.builder()
                .wholeSaleOrder(wholeSaleOrder)
                .quantity(orderItemDto.getQuantity())
                .fullPrice(orderItemDto.getFullPrice())
                .product(entityManager.getReference(Product.class,orderItemDto.getProduct()))
                .build()).toList());

        wholeSaleOrderRepository.save(wholeSaleOrder);
    }

    @Transactional
    public void updateOrderStatus(Long orderId, OrderStatus status) throws Exception {
        // Retrieve the order from the database
        WholeSaleOrder order = wholeSaleOrderRepository.findById(orderId)
                .orElseThrow(() -> new Exception("Order not found with ID: " + orderId));

        // Update the order status
        order.setOrderStatus(status);

        // If the status is APPROVED, generate an invoice
        if ("APPROVED".equalsIgnoreCase(String.valueOf(status))) {
            invoiceService.generateInvoice(order);
        }else{
             wholeSaleOrderRepository.save(order);
        }

        // Save the updated order back to the database

    }

    public List<WholeSaleOrderResponseDto> getAllOrders() {
        return wholeSaleOrderRepository.findAll().stream()
                .map(wholeSaleOrder -> WholeSaleOrderResponseDto.builder()
                        .OrderId(wholeSaleOrder.getOrderId())
                        .orderDate(wholeSaleOrder.getOrderDate())
                        .billingAddress(wholeSaleOrder.getBillingAddress())
                        .orderNotes(wholeSaleOrder.getOrderNotes())
                        .customer(wholeSaleOrder.getCustomer().getWholeSaleCustomerId())
                        .build())
                .collect(Collectors.toList());
    }
}
