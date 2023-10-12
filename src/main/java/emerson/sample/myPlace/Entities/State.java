package emerson.sample.myPlace.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table(name = "state")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int state_id;
    @Column(name = "state_name")
    private String stateName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    //SQL doesn't know about this, hibernate create the necessary statements to satisfy this relationship
    //BIDIRECTIONAL RELATIONSHIP
    @OneToMany(mappedBy = "state_id",cascade = CascadeType.ALL , orphanRemoval = true)
    private ArrayList<City> cities;

    public void addCity(City c){
        if(cities == null)cities = new ArrayList<>();
        cities.add(c);
    }
}
