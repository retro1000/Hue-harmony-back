package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_variation")
public class SupplierVariation {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierVariationId;

    @Column(name = "unit_cost", columnDefinition = "REAL DEFAULT 0 CHECK(unit_cost >= 0)", nullable = false)
    private float unitCost;

    @Column(name = "discount", columnDefinition = "REAL DEFAULT 0 CHECK(discount >= 0)")
    private float discount;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "variation_id", nullable = false)
    private Variation variation;
}
