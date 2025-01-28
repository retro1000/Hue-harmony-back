package hueHarmony.web.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InvoiceNew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceDate;
    private String invoiceNotes;
    private double discountAmount;
    private String billingAddress;
    private double totalAmount;
    private Long customerId;
    private Long orderId;
}
