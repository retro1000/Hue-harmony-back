package hueHarmony.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WholeSaleInvoicedto {
    private Long OrderId;
    private Double totalAmount;
    private String billingAddress;
}
