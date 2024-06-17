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
@Table(name = "finish")
public class Finish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int finishId;

    @Column(name = "finish_name", columnDefinition = "VARCHAR", length = 15, nullable = false, unique = true)
    private String finishName;

    @OneToMany(mappedBy = "finish")
    private List<Product> products;
}
