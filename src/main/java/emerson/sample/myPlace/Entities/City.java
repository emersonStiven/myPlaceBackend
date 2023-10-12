

package emerson.sample.myPlace.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="city", indexes = {@Index(name = "state_id", columnList = "state_id")})//check out if it's state or state_id
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name =  "state_id", referencedColumnName = "state_id")
    private State state_id;

}


