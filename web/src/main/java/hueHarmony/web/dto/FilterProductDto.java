package hueHarmony.web.dto;

import hueHarmony.web.annotation.validations.DecimalNumberValidation;
import hueHarmony.web.model.enums.data_set.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterProductDto {

    public interface whenUser{}
    public interface whenOrganization{}

    @jakarta.validation.constraints.Size(groups = {FilterOrderDto.whenUser.class}, max = 0)
    private String search;

    @Null(groups = {FilterProductDto.whenUser.class})
    private ProductCategory select = ProductCategory.PRODUCT_BARCODE;

    private Set<Brands> brands;

    private Set<Surface> surfaces;

    private Set<RoomType> roomTypes;

    private Set<ProductType> productTypes;

    private Set<Position> positions;

    private Set<Finish> finishes;

    @DecimalNumberValidation(groups = {FilterProductDto.whenOrganization.class, FilterProductDto.whenUser.class}, max = 100000F)
    private float startPrice;

    @DecimalNumberValidation(groups = {FilterProductDto.whenOrganization.class, FilterProductDto.whenUser.class}, max = 100000F)
    private float endPrice;

    @NotNull(groups = {FilterProductDto.whenOrganization.class, FilterProductDto.whenUser.class})
    private SortOptions sort = SortOptions.NEWEST;

    @Min(groups = {FilterProductDto.whenOrganization.class}, value = 1)
    private int limit;

    @Min(groups = {FilterProductDto.whenOrganization.class}, value = 1)
    private int page;
}
