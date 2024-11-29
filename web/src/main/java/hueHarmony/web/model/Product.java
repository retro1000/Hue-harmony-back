package hueHarmony.web.model;

import hueHarmony.web.model.enums.data_set.*;
import hueHarmony.web.model.enums.data_set.Finish;
import hueHarmony.web.model.enums.data_set.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name", columnDefinition = "VARCHAR", length = 256, nullable = false)
    private String productName;

    @Column(name = "product_description", columnDefinition = "TEXT", nullable = false)
    private String productDescription;

    @Column(name = "product_image", columnDefinition = "TEXT")
    private String productImageUrl;

    @Column(name = "product_price", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", nullable = false)
    private float productPrice;

    @Column(name = "product_discount", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)")
    private float productDiscount;

    @Column(name = "coat", columnDefinition = "SMALLINT DEFAULT 0 CHECK(coat >=0 )")
    private int coat;

    @Column(name = "drying_time", columnDefinition = "VARCHAR", length = 25)
    private String dryingTime;

    @Column(name = "coverage", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", length = 20)
    private float coverage;

    @Column(name = "online_limit", columnDefinition = "SMALLINT DEFAULT 0 CHECK(online_limit >= 0)")
    private int onlineLimit;

    @Column(name = "product_quantity", columnDefinition = "SMALLINT DEFAULT 0 CHECK(product_quantity >= 0)")
    private int productQuantity;

    @Column(name="productPublishedTime", nullable = false)
    private LocalDateTime productPublishedTime = getCurrentData();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_status", columnDefinition = "VARCHAR")
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false)
    private Brands brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @Column(name = "finish")
    private Finish finish;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ProductType> productType;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Surface> surfaces;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Position> positions;

    @ElementCollection
    @CollectionTable(name = "product_features", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "feature")
    private List<String> productFeatures;

   /*  @ManyToMany
    @JoinTable(name = "product_position",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "position_id", nullable = false)
    )
    private Set<Position> positions;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Position> positions;

    @ManyToMany
    @JoinTable(name = "product_product_feature",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "product_feature_id")
    )
    @Column
    private Set<ProductFeature> productFeatures;

    @Column
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PurchaseOrderProduct> purchaseOrderProduct;
*/





    @Transient
    private LocalDateTime getCurrentData(){
        return LocalDateTime.now();
    }

}
