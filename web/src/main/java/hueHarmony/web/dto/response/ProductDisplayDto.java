package hueHarmony.web.dto.response;

import hueHarmony.web.model.enums.data_set.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDisplayDto {

    private int productId;
    private String productTitle;
    private ProductStatus productStatus;
    private String productImage;
    private float price;
    private float discount;

    public ProductDisplayDto(int productId, String productTitle, ProductStatus productStatus, String productImage){
        this.productId = productId;
        this.productTitle = productTitle;
        this.productStatus = productStatus;
        this.productImage = productImage;
    }
}
