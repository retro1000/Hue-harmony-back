package hueHarmony.web.dto;

import hueHarmony.web.model.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Set<CartItemsDto> cartItems;

    private int productId;
    private int quantity;
    private String orderNote;
    private String shippingAddress;
    private String emailAddress;
    private String billingAddress;
    private Set<String> contactNos;
    private String firstName;
    private String lastName;
    private int customerId;

    private String paymentMethodId;

    @NotNull
    private PaymentMethod paymentMethod;

}
