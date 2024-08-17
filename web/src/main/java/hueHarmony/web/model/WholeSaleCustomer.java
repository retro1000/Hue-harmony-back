package hueHarmony.web.model;

import hueHarmony.web.model.enums.WholeSaleCustomerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "whole_sale_customer")
public class WholeSaleCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wholeSaleCustomerId;

    @Column(name = "land_phone", columnDefinition = "VARCHAR", nullable = false, length = 15)
    private String landPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "whole_sale_customer_status", columnDefinition = "VARCHAR", nullable = false, length = 10)
    private WholeSaleCustomerStatus wholeSaleCustomerStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id", nullable = false)
    private User createdUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "approved_user_id", nullable = false)
    private User approvedUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
