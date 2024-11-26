package hueHarmony.web.model;

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

//    @Column(name = "product_image", nullable = false, columnDefinition = "TEXT")
//    private String productImage;

    @Column(name = "coat", columnDefinition = "SMALLINT DEFAULT 0 CHECK(coat >=0 )")
    private int coat;

    @Column(name = "drying_time", columnDefinition = "VARCHAR", length = 25)
    private String dryingTime;

    @Column(name = "coverage", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", length = 20)
    private float coverage;

    @OneToMany(mappedBy = "product")
    @Column
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "product")
    @Column
    private List<ProductVariation> productVariations;

    @ManyToOne
//    @JoinColumn(name = "brand_id", nullable = true)
    private Brand brand;

    @ManyToOne
//    @JoinColumn(name = "room_type_id", nullable = true)
    private RoomType roomType;

    @ManyToOne
//    @JoinColumn(name = "finish_id")
    private Finish finish;

    @ManyToOne
//    @JoinColumn(name = "product_type", nullable = true)
    private ProductType productType;

    @ManyToMany
    @JoinTable(name = "product_surface",
        joinColumns = @JoinColumn(name = "product_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "surface_id", nullable = true)
    )
    private Set<Surface> surfaces;

    @ManyToMany
    @JoinTable(name = "product_position",
            joinColumns = @JoinColumn(name = "product_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "position_id", nullable = true)
    )
    private Set<Position> positions;

    @ManyToMany
    @JoinTable(name = "product_product_feature",
            joinColumns = @JoinColumn(name = "product_id", nullable = true),
            inverseJoinColumns = @JoinColumn(name = "product_feature_id", nullable = true)
    )
    @Column
    private Set<ProductFeature> productFeatures;

    @Column
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseOrderProduct> purchaseOrderProduct;

}
