package hueHarmony.web.model;

import PROJ.VIVO.model.enums.DeliveryServiceStatus;
import hueHarmony.web.model.enums.DeliveryServiceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "delivery_service")
public class DeliveryService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryServiceId;

    @Column(name = "delivery_service_name", columnDefinition = "VARCHAR", length = 40, nullable = false, unique = true)
    private String deliveryServiceName;

    @Column(name = "delivery_service_orders", columnDefinition = "SMALLINT DEFAULT 0 CHECK(delivery_service_orders >= 0)")
    private int deliveryServiceOrders;

    @Column(name = "delivery_service_cancel_orders", columnDefinition = "SMALLINT DEFAULT 0 CHECK(delivery_service_cancel_orders >= 0)")
    private int deliveryServiceCancelOrders;

    @Column(name = "price_per_extra_kg", columnDefinition = "REAL DEFAULT 0.0 CHECK(price_per_extra_kg >= 0)", nullable = false)
    private float pricePerExtraKg;

    @Column(name = "delivery_service_status", columnDefinition = "VARCHAR", length = 13, nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryServiceStatus deliveryServiceStatus;

    @OneToMany(mappedBy = "deliveryService", cascade = CascadeType.MERGE)
    private List<ActiveWaybill> activeWaybills;

    @OneToMany(mappedBy = "deliveryService", cascade = CascadeType.MERGE)
    private List<Order> orders;

    @OneToMany(mappedBy = "deliveryService", cascade = CascadeType.ALL)
    private List<DeliveryServiceDistrict> deliveryServiceDistricts;
}
