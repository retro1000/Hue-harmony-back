package hueHarmony.web.dto.response_dto;

import hueHarmony.web.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDisplayDto {

    private int purchaseOrderId;
    private String description;
    private Status status;
    private String supplier;
}
