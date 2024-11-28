package hueHarmony.web.model;

import hueHarmony.web.annotation.validations.DataExistingValidation;
import hueHarmony.web.annotation.validations.DecimalNumberValidation;
import hueHarmony.web.annotation.validations.NameValidation;
import hueHarmony.web.model.enums.data_set.Brands;
import hueHarmony.web.model.enums.data_set.Color;
import hueHarmony.web.model.enums.data_set.Size;
import hueHarmony.web.service.VariationService;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VariationDto {

    public interface onCreate{}
    public interface onUpdate{}
    public interface onDelete{}

    @NotNull(groups = {onDelete.class, onUpdate.class})
    @DataExistingValidation(groups = {onDelete.class, onUpdate.class}, service = VariationService.class, method = "")
    private int variationId;

    @NotNull(groups = {onDelete.class, onUpdate.class})
    @NameValidation(groups = {onCreate.class, onUpdate.class}, required = true)
    private String variationName;

    @NotNull(groups = {onCreate.class})
    private Color color;

    @NotNull(groups = {onCreate.class})
    private Size size;

    @NotNull(groups = {onCreate.class})
    private Brands brand;

    @NotNull(groups = {onCreate.class})
    @DecimalNumberValidation(groups = {onCreate.class, onUpdate.class}, max = 100000F)
    private float price;

    @NotNull(groups = {onCreate.class})
    @DecimalNumberValidation(groups = {onCreate.class, onUpdate.class}, max = 100000F)
    private float discount;
}
