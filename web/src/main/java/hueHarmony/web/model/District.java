package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "district")
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int districtId;

    @Column(name = "district_name", columnDefinition = "VARCHAR", length = 15, unique = true, nullable = false)
    private String districtName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district", cascade = CascadeType.ALL)
    private List<City> cities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district", cascade = CascadeType.MERGE)
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
    private List<DeliveryServiceDistrict> deliveryServiceDistricts;
}
