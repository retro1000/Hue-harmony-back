package hueHarmony.web.model;

import hueHarmony.web.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PosOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private Long phoneNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PosOrderItem> items;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private BigDecimal subTotal;

    @Column(nullable = false)
    private BigDecimal discount;

    @Column(nullable = false)
    private String paymentMethod; // e.g., "Cash", "Card"

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)  // Store enum as a String in the database
    private OrderStatus orderStatus = OrderStatus.COMPLETED;// e.g., "Pending", "Completed"

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "cashier_id", nullable = false)
    private User cashier;

}
