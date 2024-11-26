package hueHarmony.web.dto;

import hueHarmony.web.model.PosOrderItem;
import hueHarmony.web.model.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosOrderDto {

    private Long id;
    private LocalDateTime orderDate;
    private String customerName;
    private Long phoneNumber;
    private List<PosOrderItem> items; // Use a corresponding DTO for PosOrderItem
    private BigDecimal total;
    private BigDecimal subTotal;
    private BigDecimal discount;
    private String paymentMethod;
    private OrderStatus orderStatus;
    private Long cashierId; // Assuming only the ID of the cashier is needed
}

