package hueHarmony.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReceivedProductDto {

    private int id; // ID of the received product
    private int grnId; // Associated GRN ID
    private int productId; // Product ID
    private int quantityReceived; // Quantity of the product received
}
