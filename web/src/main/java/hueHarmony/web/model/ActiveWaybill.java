package hueHarmony.web.model;

import hueHarmony.web.model.enums.ActiveWaybillStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "active_waybill")
public class ActiveWaybill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activeWaybillId;

    @NotEmpty(message = "Waybill cannot be empty.")
    @Column(name = "active_waybill", nullable = false, unique = true)
    private long activeWaybill;

    @Column(name = "active_waybill_status", columnDefinition = "VARCHAR", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private ActiveWaybillStatus activeWaybillStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "activeWaybill", cascade = CascadeType.MERGE)
    private Set<Order> orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_service_id", nullable = false)
    private DeliveryService deliveryService;
}
