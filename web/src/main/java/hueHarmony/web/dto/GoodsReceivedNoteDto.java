package hueHarmony.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsReceivedNoteDto {

    private long grnId; // ID of the GRN
    private long purchaseOrderId; // Associated purchase order ID
    private List<GoodsReceivedProductDto> receivedProducts; // List of received products
    private Date receivedDate; // Date the goods were received
    private String remarks; // Remarks or comments
    private boolean isComplete; // Indicates if the GRN is complete
}
