package hueHarmony.web.dto;


import hueHarmony.web.model.enums.data_set.ProductType;
import lombok.Data;

import hueHarmony.web.model.enums.data_set.Brands;
import hueHarmony.web.model.enums.data_set.Finish;
import hueHarmony.web.model.enums.data_set.RoomType;

@Data
public class AddProductDto {

    private String productName;

    private String productDescription;

    private float productPrice;

    private float productDiscount;

    private int coat;

    private int dryingTime;

    private int coverage;

    private String productStatus;

    private Brands brand;

    private RoomType roomType;

    private Finish finish;

    private ProductType productType;

    private String[] surfaces;

    private String[] positions;

    private String[] productFeatures;
}
