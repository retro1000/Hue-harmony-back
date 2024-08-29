package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(name = "product_name", columnDefinition = "VARCHAR", length = 256, nullable = false)
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT", nullable = false)
    private String productDescription;

    @Column(name = "product_image", nullable = true, columnDefinition = "TEXT")
    private String productImageUrl;

    @Column(name = "coat", columnDefinition = "SMALLINT DEFAULT 0 CHECK(coat >=0 )")
    private int coat;

    @Column(name="starting_price",columnDefinition = "VARCHAR")
    private String startingPrice = "RS:0";

    @Column(name="product_discount",columnDefinition = "INT")
    private int productDiscount;

    @Column(name="product_status",columnDefinition = "TEXT")
    private String productStatus;


    @Column(name = "drying_time", columnDefinition = "VARCHAR", length = 25)
    private String dryingTime;

    @Column(name = "coverage", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", length = 20)
    private float coverage;

    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages;

    @OneToMany(mappedBy = "product")
    private List<ProductVariation> productVariations;

   /* @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;*/

    @Column(name = "brand",columnDefinition = "VARCHAR",length = 20)
    private String brand;

    /*@ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;*/

    @Column(name = "room_type",columnDefinition = "VARCHAR",length = 30)
    private String roomType;

    @ManyToOne
    @JoinColumn(name = "finish_id")
    private Finish finish;

    @ManyToOne
    @JoinColumn(name = "product_type", nullable = false)
    private ProductType productType;

    @ManyToMany
    @JoinTable(name = "product_surface",
        joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "surface_id", nullable = false)
    )
    private Set<Surface> surfaces;

    @ManyToMany
    @JoinTable(name = "product_position",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "position_id", nullable = false)
    )
    private Set<Position> positions;

    @ManyToMany
    @JoinTable(name = "product_product_feature",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_feature_id", nullable = false)
    )
    private Set<ProductFeature> productFeatures;
}
