package hueHarmony.web.model;

import hueHarmony.web.model.enums.OrderPaymentType;
import hueHarmony.web.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "order_no", nullable = false, unique = true)
    private long orderNo;

    @Column(name = "order_note", columnDefinition = "TEXT")
    private String orderNote;

    @Column(name = "waybill")
    private long waybill;

    @Column(name = "billing_address", columnDefinition = "TEXT", nullable = false)
    private String billingAddress;

    @Column(name = "shipping_address", columnDefinition = "TEXT", nullable = false)
    private String shippingAddress;

    @Column(name = "order_status", columnDefinition = "VARCHAR", length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @Column(name = "order_payment_type", columnDefinition = "VARCHAR", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderPaymentType orderPaymentType;

    @OneToMany
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private List<OrderVariation> orderVariations;

//    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    private List<OrderPayment> orderPayments;
//
//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    //    @JoinColumn(name = "order_id", nullable = false)
//    private List<PROJ.VIVO.model.OrderStatus> orderStatusTimeLine;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "waybill_id")
    private ActiveWaybill activeWaybill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_service_id", nullable = false)
    private DeliveryService deliveryService;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdUser;





    @Transient
    private static final Random random = new Random();

    @Transient
    private static long generateUniqueOrderNumber() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = dateFormat.format(new Date());
        int randomNum = random.nextInt(10000);
        return Long.parseLong(timestamp + String.format("%04d", randomNum));
    }

//    @PrePersist
//    public void prePersistOrderNo(){
//        if(this.orderNo==0) this.orderNo = generateUniqueOrderNumber();
//    }
}
