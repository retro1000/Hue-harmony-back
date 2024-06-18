package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentId;

    @Column(name = "discount", columnDefinition = "REAL DEFAULT 0.00 CHECK(item_cost >= 0)", nullable = false)
    private float discount = 0;

    @OneToMany
    private List<ShipmentVariation> shipmentVariations;
}
