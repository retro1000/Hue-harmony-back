package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

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

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

//    @ManyToOne
//    @JoinColumn(name = "room_type_id", nullable = false)
//    private RoomType roomType;

    @ManyToOne
    @JoinColumn(name = "finish_id", nullable = false)
    private Finish finish;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

//    @ManyToOne
//    @JoinColumn(name = "product_type_id", nullable = false)
//    private ProductType productType;
//
//    @ManyToMany
//    @JoinTable(name = "variation_position",
//            joinColumns = @JoinColumn(name = "position_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "variation_id", nullable = false)
//    )
//    private Set<Position> positions;
//
//    @ManyToMany
//    @JoinTable(name = "variation_surface",
//            joinColumns = @JoinColumn(name = "surface_id", nullable = false),
//            inverseJoinColumns = @JoinColumn(name = "variation_id", nullable = false)
//    )
//    private Set<Surface> surfaces;
}
