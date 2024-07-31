package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sub_color")
public class SubColor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subColorId;

    @Column(name = "sub_color_name", nullable = false, columnDefinition = "VARCHAR", length = 20, unique = true)
    private String subColorName;
}
