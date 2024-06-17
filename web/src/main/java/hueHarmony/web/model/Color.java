package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorId;

    @Column(name = "color_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String colorName;

    @ManyToMany
    private List<Variation> variations;
}
