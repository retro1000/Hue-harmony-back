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
@Table(name = "surface")
public class Surface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int surfaceId;

    @Column(name = "surface_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String surfaceName;

    @ManyToMany
    private List<Surface> surfaces;
}
