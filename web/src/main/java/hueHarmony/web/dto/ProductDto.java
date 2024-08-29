package hueHarmony.web.dto;

import com.fasterxml.jackson.annotation.JsonView;
import hueHarmony.web.annotation.validations.DataExistingListValidation;
import hueHarmony.web.model.Position;
import hueHarmony.web.model.ProductFeature;
import hueHarmony.web.model.Surface;
import hueHarmony.web.service.ProductService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

    private String productImageUrl;

    private String startingPrice;

    private String productStatus;

    private int productDiscount;

    private String productDescription;

    private String productCategory;

    private String productBrand;

    private String productPrice;

    private String productSize;

    private String productType;

    private String dryingTime;

    private String roomType;

    private Set<ProductFeature> productFeatures;


    private Set<Surface> surfaces;


    private Set<Position> positions;

    public ProductDto(int productId, String productName, String productImageUrl, String startingPrice, String productStatus, int productDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.startingPrice = startingPrice;
        this.productStatus = productStatus;
        this.productDiscount = productDiscount;
    }

}
