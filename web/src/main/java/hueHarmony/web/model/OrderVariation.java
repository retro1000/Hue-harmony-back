package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_variation")
public class OrderVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderVariationId;
}
