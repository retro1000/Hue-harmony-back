package hueHarmony.web.model;

import hueHarmony.web.model.enums.PaymentMethod;
import hueHarmony.web.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Entity
@Table(name="whole_sale_invoice")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WholeSaleInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generating invoiceId
    @Column(name = "invoice_id", nullable = false, unique = true)
    private Long invoiceId;  // Unique identifier for the invoice

    // Reference to the associated WholeSaleOrder
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", nullable = false)
    private WholeSaleOrder wholeSaleOrder;  // Associated order for the invoice

    @Temporal(TemporalType.DATE)
    @Column(name = "invoice_date", nullable = false)
    private Date invoiceDate;  // Date when the invoice was generated

    @Column(name = "total_amount", nullable = false)
    private Double totalAmount;  // Total amount for the invoice, reflecting the order total

    @Column(name = "discount_amount")
    private Double discountAmount;  // Discount applied to the invoice, if any

    @Column(name = "net_amount", nullable = false)
    private Double netAmount;  // Net payable amount after applying discounts

    @Column(name = "billing_address", length = 500)
    private String billingAddress;  // Billing address on the invoice

    @Column(name = "payment_method", length = 50)
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;  // Payment method used for the order

    @Column(name = "payment_status", length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PaymentStatus paymentStatus= PaymentStatus.PENDING;  // Payment status at the time of invoice generation

    @Column(name = "notes", length = 1000)
    private String notes;

    @OneToMany(mappedBy = "wholeSaleInvoice", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<DebitNote> debitNotes;

    @OneToMany(mappedBy = "wholeSaleInvoice", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<CreditNote> creditNotes;
}
