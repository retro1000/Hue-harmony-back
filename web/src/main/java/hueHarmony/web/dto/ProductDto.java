package hueHarmony.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import hueHarmony.web.annotation.validations.DataExistingListValidation;
import hueHarmony.web.model.enums.data_set.*;
import hueHarmony.web.service.ProductService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    public interface onCreate{}
    public interface onUpdate{}
    public interface onDelete{}
    public interface onListView{}
    public interface onView{}
    public interface onStatusUpdate{}



    @JsonView({onUpdate.class,onDelete.class,onStatusUpdate.class})
    @DataExistingListValidation(groups = {onUpdate.class,onDelete.class,onStatusUpdate.class},service = ProductService.class,method = "isProductExist")
    private int productId;

    @JsonView({onCreate.class,onListView.class,onView.class,onUpdate.class})
    @NotNull(groups = {onCreate.class,onUpdate.class},message = "Product name cannot be Empty")
    @DataExistingListValidation(groups = {onUpdate.class,onDelete.class,onStatusUpdate.class},service = ProductService.class,method = "isProductExist")
    private String productName;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private MultipartFile productImage;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private String productImageUrl;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private float startingPrice;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private ProductStatus productStatus;

    @JsonView({onView.class,onUpdate.class})
    private int productDiscount;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private String productDescription;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private String productCategory;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private Brands productBrand;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private String productPrice;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private String productSize;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private ProductType productType;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private String dryingTime;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private RoomType roomType;

    /*@JsonView({onView.class,onCreate.class,onUpdate.class})
    private Set<ProductFeature> productFeatures;

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private Set<Surface> surfaces;*/

    @JsonView({onView.class,onCreate.class,onUpdate.class})
    private Set<Position> positions;

    public ProductDto(int productId, String productName, String productImageUrl, float startingPrice, ProductStatus productStatus, int productDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.startingPrice = startingPrice;
        this.productStatus = productStatus;
        this.productDiscount = productDiscount;
    }

}
