package hueHarmony.web.dto;

import hueHarmony.web.model.Product;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderProductDto {

    private int quantity;
    private int product;
    private float fullPrice;
}
