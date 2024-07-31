package hueHarmony.web.model;

import jakarta.persistence.*;
import java.util.Set;

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

    // Getters and setters
}
