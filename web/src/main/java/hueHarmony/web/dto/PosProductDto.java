package hueHarmony.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PosProductDto {
    private int productId;
    private String productName;
    private List<String> productImages;
    private Float productDiscount;
    private Float productPrice;

//    public PosProductDto(int productId, String productName, float productPrice, float productDiscount) {
//        this.productId = productId;
//        this.productName = productName;
//        this.productPrice = productPrice;
//        this.productDiscount = productDiscount;
//    }

}
