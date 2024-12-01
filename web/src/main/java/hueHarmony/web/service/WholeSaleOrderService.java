package hueHarmony.web.service;

import hueHarmony.web.dto.WholeSaleOrderdto;
import hueHarmony.web.model.Product;
import hueHarmony.web.model.SalesOrderProduct;
import hueHarmony.web.model.WholeSaleCustomer;
import hueHarmony.web.model.WholeSaleOrder;
import hueHarmony.web.repository.WholeSaleOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WholeSaleOrderService {

    private final WholeSaleOrderRepository wholeSaleOrderRepository;

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
}
