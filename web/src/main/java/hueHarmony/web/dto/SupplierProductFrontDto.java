package hueHarmony.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierProductFrontDto {
    private int id;
    private String name;
    private String image;
    private int quantity;

    public SupplierProductFrontDto(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
