package hueHarmony.web.dto.response;

import hueHarmony.web.model.enums.data_set.ProductStatus;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductUserDisplayDto extends ProductDisplayDto{

    private boolean isSale;
    private boolean isNew;
    private float reviewRate;
    private int totalReviews;
    private float discount;

    public ProductUserDisplayDto(
            int productId,
            String productTitle,
            ProductStatus productStatus,
            String productImageUrl,
            float reviewRate,
            int totalReviews
    ){
        super(productId, productTitle, productStatus, productImageUrl);
        this.reviewRate = reviewRate;
        this.totalReviews = totalReviews;
    }

    public ProductUserDisplayDto(
            int productId,
            String productTitle,
            ProductStatus productStatus,
            String productImageUrl,
            float[] priceRange,
            boolean isSale,
            boolean isNew,
            float reviewRate,
            int totalReviews,
            float discount
    ){
        super(productId, productTitle, productStatus, productImageUrl, priceRange);
        this.isSale = isSale;
        this.isNew = isNew;
        this.reviewRate = reviewRate;
        this.totalReviews = totalReviews;
        this.discount = discount;
    }
}
