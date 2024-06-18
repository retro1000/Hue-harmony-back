package hueHarmony.web.model;

import hueHarmony.web.model.enums.ShipmentVariationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment_variation")
public class ShipmentVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentVariationId;

    @Column(name = "expected_quantity", columnDefinition = "SMALLINT DEFAULT 0 CHECK(expected_quantity >= 0)", nullable = false)
    private int expectedQuantity;

    @Column(name = "received_quantity", columnDefinition = "SMALLINT DEFAULT 0 CHECK(received_quantity >= 0)", nullable = false)
    private int receivedQuantity;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "item_cost", columnDefinition = "REAL DEFAULT 0.00 CHECK(item_cost >= 0)", nullable = false)
    private float itemCost = 0;

    @Column(name = "discount", columnDefinition = "REAL DEFAULT 0.00 CHECK(item_cost >= 0)", nullable = false)
    private float discount = 0;

    @Column(name = "shipment_variation_status", columnDefinition = "VARCHAR", length = 15, nullable = false)
    @Enumerated(EnumType.STRING)
    private ShipmentVariationStatus shipmentVariationStatus;

//    @OneToMany(mappedBy = "shipmentVariation")
//    private List<ShipmentVariationNote> shipmentVariationNoteList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "variation_id", nullable = false)
    private Variation variation;
//cascade
}
