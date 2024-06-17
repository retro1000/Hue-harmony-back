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
@Table(name = "variation")
public class Variation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int variationId;

    @OneToMany(mappedBy = "variation")
    private List<ProductVariation> productVariations;

    @ManyToMany
    @JoinTable(name = "variation_color",
            joinColumns = @JoinColumn(name = "variation_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "color_id", nullable = false)
    )
    private List<Color> colors;

    @ManyToMany
    @JoinTable(name = "variation_size",
            joinColumns = @JoinColumn(name = "variation_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "size_id", nullable = false)
    )
    private List<Size> sizes;
}
