package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="loyalty")
public class Loyalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loyaltyId;

    @Column()
    private float loyaltyPoints;

    @Column(name = "contact_no", columnDefinition = "VARCHAR", length = 10)
    private String contactNo;
}
