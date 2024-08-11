package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deliver_service_district")
public class DeliveryServiceDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryServiceDistrictId;

    @Column(name = "deliver_charge", columnDefinition = "REAL DEFAULT 0 CHECK(deliver_charge >= 0)", nullable = false)
    private float deliverCharge = 0;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = false)
    private District district;

    @ManyToOne
    @JoinColumn(name = "delivery_service_id", nullable = false)
    private DeliveryService deliveryService;
}
