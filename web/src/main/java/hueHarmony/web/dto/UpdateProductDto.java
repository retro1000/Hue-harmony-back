package hueHarmony.web.dto;

import hueHarmony.web.model.enums.data_set.Brands;
import hueHarmony.web.model.enums.data_set.Finish;
import hueHarmony.web.model.enums.data_set.Position;
import hueHarmony.web.model.enums.data_set.ProductStatus;
import hueHarmony.web.model.enums.data_set.ProductType;
import hueHarmony.web.model.enums.data_set.RoomType;
import hueHarmony.web.model.enums.data_set.Surface;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UpdateProductDto {
    private String productName;
    private String productDescription;
    private float productPrice;
    private float productDiscount;
    private int coat;
    private String dryingTime;
    private float coverage;
    private int onlineLimit;
    private int productQuantity;
    private ProductStatus productStatus;
    private Brands brand;
    private RoomType roomType;
    private Finish finish;
    private List<ProductType> productTypes;
    private List<Surface> surfaces;
    private Set<Position> positions;
    private List<String> productFeatures;
    private List<String> images; // Base64 strings of images
}

