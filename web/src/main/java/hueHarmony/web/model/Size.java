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
@Table(name = "size")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeId;

    @Column(name = "size_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String sizeName;

    @ManyToMany
    private List<Variation> variations;
}
