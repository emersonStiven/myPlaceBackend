package emerson.sample.myPlace.DataBaseEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import javax.swing.*;

@Entity
@Immutable
@Table( name = "geography_info")
@AllArgsConstructor
@NoArgsConstructor
public class GeographyInfoView {
    @Id
    private int id;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "city_name")
    private String cityName;

    public String getStateName(){
        return this.stateName;
    }
    public String getCityName(){
        return this.cityName;
    }
    public int getId(){return this.id;}
}
