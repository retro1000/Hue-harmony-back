package hueHarmony.web.dto.response;

import hueHarmony.web.model.enums.data_set.Brands;
import hueHarmony.web.model.enums.data_set.Finish;
import hueHarmony.web.model.enums.data_set.RoomType;

import java.util.List;

public class ProductResponseDto {
    private int id;

    private String productName;

    private String productDescription;

    private float productPrice;

    private float productDiscount;

    private int coat;

    private int dryingTime;

    private int coverage;

    private float onlineLimit;

    private float productQuantity;

    private String productStatus;

    private Brands brand;

    private RoomType roomType;

    private Finish finish;

    private List<String> productTypes;

    private List<String> surfaces;

    private List<String> positions;

    private List<String> productFeatures;

    private List<String> productImageUrls;
}
