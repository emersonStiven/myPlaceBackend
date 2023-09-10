

package emerson.sample.myPlace.DataBaseEntities;

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
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int city_id;
    @Column(name = "city_name")
    private String cityName;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name =  "state_id", referencedColumnName = "state_id")
    private State state;

}


