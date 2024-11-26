package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

   /* @ManyToMany(mappedBy = "surfaces")
    private Set<Product> products = new HashSet<>();*/


}
