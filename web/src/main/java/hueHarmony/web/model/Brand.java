package hueHarmony.web.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "brand_product_variation",
            joinColumns = @JoinColumn(name = "brand_id"),
            inverseJoinColumns = @JoinColumn(name = "product_variation_id"))
    private Set<ProductVariation> productVariations; // Ensure this matches ProductVariation

    // Getters and setters
}
