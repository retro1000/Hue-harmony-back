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
@Table(name = "room_type")
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomTypeId;

    @Column(name = "room_type_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String roomTypeName;

    @OneToMany(mappedBy = "roomType")
    private List<Product> products;
}
