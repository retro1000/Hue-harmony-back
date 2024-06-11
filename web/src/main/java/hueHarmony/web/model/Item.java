package hueHarmony.web.model;

import hueHarmony.web.model.Enum.ItemStatus;
import hueHarmony.web.model.Enum.Manufacturer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @Column(name = "barcode", unique = true, nullable = false)
    private long barcode;

    @Column(name = "item_status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;

    //possible relationship with warehouse
    @Column(name = "item_position", columnDefinition = "VARCHAR", length = 200, nullable = false)
    private String itemPosition;

    @Column(name = "expire_date", columnDefinition = "DATE", nullable = false)
    private LocalDate expireDate;

    @Column(name = "manufacture_date", columnDefinition = "DATE", nullable = false)
    private LocalDate manufactureDate;

    @Column(name = "manufacturer", nullable = false, length = 40)
    @Enumerated(EnumType.STRING)
    private Manufacturer manufacturer;



    //Product relationship
    //Stock number object relationship
    //Variation relationship
}
