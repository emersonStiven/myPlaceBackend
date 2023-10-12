package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location_listing",
        indexes = @Index(name = "idx_location_listing" , columnList = "listing_id"))
public class LocationListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    @OneToOne(targetEntity = Listing.class,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            optional = false)
    @JoinColumn(name = "listing_id",unique = true,
            referencedColumnName = "listing_id",
            foreignKey = @ForeignKey(name = "frgKey_location_listing"))
    private Listing listing_id;

}
