package hueHarmony.web.dto;

import hueHarmony.web.model.enums.WholeSaleCustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class WholeSaleCustomersDto {

    private int customerId;
    private String firstName;
    private String lastName;
    private String businessAddress;
    private String shippingAddress;
    private String contactNo;
    private String contactPersonNumber;
    private String emailAddress;
    private Double totalPurchases;
    private WholeSaleCustomerStatus status;
}
