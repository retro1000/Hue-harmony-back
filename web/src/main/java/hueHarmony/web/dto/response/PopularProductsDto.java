package hueHarmony.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PopularProductsDto {

    private Integer productId;
    private String productName;
    private List<String> imageIds;
    private Float productPrice;
    private Float productDiscount;
    private String productDescription;

    public PopularProductsDto(Integer productId, String productName, List<String> imageIds, Float productPrice, Float productDiscount) {
        this.productId = productId;
        this.productName = productName;
        this.imageIds = imageIds;
        this.productPrice = productPrice;
        this.productDiscount = productDiscount;
    }
}
