package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Entity
@Table(name = "location_listing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationListing {

    @Id
    private int location_id;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "city", nullable = false, length = 255)
    private String city;

    @Column(name = "state_province", nullable = false, length = 255)
    private String state_province;

    @Column(name = "country", nullable = false, length = 255)
    private String country;

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "location_desc", columnDefinition = "TEXT")
    private String location_desc;

    @Column(name = "listing_id", nullable = false)
    private int listing_id;

}
