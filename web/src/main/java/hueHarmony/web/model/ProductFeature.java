package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_feature")
public class ProductFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productFeatureId;

    @Column(name = "product_feature_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String productFeatureName;
}
