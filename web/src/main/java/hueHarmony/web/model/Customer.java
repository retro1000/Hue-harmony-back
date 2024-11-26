package hueHarmony.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(name = "first_name", columnDefinition = "VARCHAR", length = 60)
    private String firstName;

    @Column(name = "last_name", columnDefinition = "VARCHAR", length = 60)
    private String lastName;

    @Column(name = "contact_no", columnDefinition = "VARCHAR", length = 10)
    private String contactNo;


//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.MERGE)
//    private List<Order> orders;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
//    private List<LinkedCard> linkedCards;

}
