package hueHarmony.web.dto;

import hueHarmony.web.model.enums.data_set.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDto {
    private int productId;
    private String productName;
    private String productColor;
    private String productDescription;
    private float productSize;
    private float productPrice;
    private float productDiscount;
    private int coat;
    private int dryingTime;
    private float coverage;
    private float onlineLimit;
    private int productQuantity;
    private LocalDateTime productPublishedTime;
    private ProductStatus productStatus;
    private List<String> imageIds;
    private Brands brand;
    private List<RoomType> roomType;
    private Finish finish;
    private List<ProductType> productType;
    private List<Surface> surfaces;
    private List<Position> positions;
    private List<String> productFeatures;
}
