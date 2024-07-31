package hueHarmony.web.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product")
    private Set<ProductVariation> productVariations;

    // Getters and setters
}
