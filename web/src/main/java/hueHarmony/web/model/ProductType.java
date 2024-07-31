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
@Table(name = "product_type")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;

    @Column(name = "product_type_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String productTypeName;

    @OneToMany(mappedBy = "productType")
    private List<Product> products;
}
