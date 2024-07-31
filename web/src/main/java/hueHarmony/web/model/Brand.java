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
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    @Column(name = "brand_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String brandName;

    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
