package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
}
