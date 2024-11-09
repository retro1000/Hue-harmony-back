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
@Table(name = "product_variation")
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productVariationId;

    @Column(name = "unit_price", columnDefinition = "REAL DEFAULT 0 CHECK(unit_price >= 0)", nullable = false)
    private float unitPrice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "variation_id", nullable = false)
    private Variation variation;

    @ManyToOne
    @JoinColumn(name = "shipment_varition_id", nullable = false)
    private ShipmentVariation shipmentVariation;

}
