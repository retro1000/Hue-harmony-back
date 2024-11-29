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

import java.util.ArrayList;
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

    @Column(name = "product_image_url", nullable = false, columnDefinition = "TEXT")
    private String productImageUrl;

    @Column(name = "coat", columnDefinition = "SMALLINT DEFAULT 0 CHECK(coat >= 0 )")
    private int coat;

    @Column(name = "drying_time", columnDefinition = "VARCHAR", length = 25)
    private String dryingTime;

    @Column(name = "coverage", columnDefinition = "REAL DEFAULT 0 CHECK(coverage >= 0)", length = 20)
    private float coverage;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "product_status", columnDefinition = "VARCHAR")
    private ProductStatus productStatus;
//
//    @ElementCollection
//    @Column(name = "image_ids")
//    private List<String> imageIds = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductImages> productImages;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "brand", nullable = false)
    private Brands brand;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "room_type", nullable = false)
    private RoomType roomType;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "finish")
    private Finish finish;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ProductType> productType;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Surface> surfaces;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Position> positions;

    @ElementCollection
    @CollectionTable(name = "product_features", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "feature")
    private List<String> productFeatures;
}
