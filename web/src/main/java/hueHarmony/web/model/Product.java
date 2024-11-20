package hueHarmony.web.model;

import hueHarmony.web.model.enums.data_set.Brands;
import hueHarmony.web.model.enums.data_set.Finish;
import hueHarmony.web.model.enums.data_set.Position;
import hueHarmony.web.model.enums.data_set.ProductStatus;
import hueHarmony.web.model.enums.data_set.ProductType;
import hueHarmony.web.model.enums.data_set.RoomType;
import hueHarmony.web.model.enums.data_set.Surface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name", columnDefinition = "VARCHAR", length = 256, nullable = false)
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT", nullable = false)
    private String productDescription;

    @Column(name = "product_price", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", nullable = false)
    private float productPrice;

    @Column(name = "product_discount", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)")
    private float productDiscount;

//    @Column(name = "product_image", nullable = false, columnDefinition = "TEXT")
//    private String productImage;

    @Column(name = "coat", columnDefinition = "SMALLINT DEFAULT 0 CHECK(coat >=0 )")
    private int coat;

    @Column(name = "drying_time", columnDefinition = "VARCHAR", length = 25)
    private String dryingTime;

    @Column(name = "coverage", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", length = 20)
    private float coverage;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_status", columnDefinition = "VARCHAR", length = 10, nullable = false)
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "product")
    private List<ProductVariation> productVariations;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brands brand;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "finish_id")
    private Finish finish;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "product_type", nullable = false)
    private ProductType productType;

    @ElementCollection(targetClass = Surface.class)
    @CollectionTable(name = "product_surface",
            joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "surface", nullable = false)
    private Set<Surface> surfaces;

    @ElementCollection(targetClass = Position.class)
    @CollectionTable(name = "product_position",
            joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "position", nullable = false)
    private Set<Position> positions;


    @ManyToMany
    @JoinTable(name = "product_product_feature",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_feature_id", nullable = false)
    )
    private Set<ProductFeature> productFeatures;
}
