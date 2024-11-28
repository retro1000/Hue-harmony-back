package hueHarmony.web.model;

import jakarta.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Correct the relationship mapping
    @ManyToMany(mappedBy = "productVariations")
    private Set<Brand> brands; // Correct the type to match the mapped entity

    @ManyToOne
    @JoinColumn(name = "shipment_variation_id", nullable = false)
    private ShipmentVariation shipmentVariation;
//
//    @OneToMany(mappedBy = "brand")
//    private List<Product> products;

//    @ManyToOne
//    @JoinColumn(name = "brand_id", nullable = false)
//    private Brand brand;

   @Column(name = "size")
    private int size;

    @Column(name = "color")
    private String color;
}
