package emerson.sample.myPlace.DataBaseEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "listings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Listing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int listing_id;

    @Column(name = "host_id", nullable = false)
    private int host_id;

    @Column(name = "pricing_id", nullable = false)
    private int pricing_id;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private LocationListing location;

    @Column(name = "bookings_ctn", columnDefinition = "int default 0")
    private int bookings_ctn;

    @Column(name = "views_ctn", columnDefinition = "int default 0")
    private int views_ctn;

    @Column(name = "property_name", nullable = false, length = 50)
    private String property_name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;

    @Column(name = "neighborhood_overview", length = 255)
    private String neighborhood_overview;

    @Column(name = "notes", length = 255)
    private String notes;

    @Column(name = "transit", length = 255)
    private String transit;

    @Column(name = "bathroom_ctn", nullable = false)
    private int bathroom_ctn;

    @Column(name = "bedroom_ctn", nullable = false)
    private int bedroom_ctn;

    @Column(name = "bed_ctn", nullable = false)
    private int bed_ctn;

    @Column(name = "guest_num", nullable = false)
    private int guest_num;

    @Column(name = "min_nights", nullable = false, columnDefinition = "int default 1")
    private int min_nights;

    @Column(name = "max_nights")
    private Integer max_nights;

    @Column(name = "cancelation_policy", nullable = false)
    private int cancelation_policy;

    @Column(name = "host_rules", length = 255)
    private String host_rules;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "last_updated", nullable = false, columnDefinition = "datetime on update now()")
    private LocalDateTime last_updated;

    @Column(name = "property_type", nullable = false)
    private int property_type;

    @Column(name = "room_type", nullable = false)
    private int room_type;

    @Column(name = "tax_rate", nullable = false)
    private int tax_rate;

    @Column(name = "reviews_ctn", columnDefinition = "int default 0")
    private int reviews_ctn;

    @Column(name = "ave_reviews", columnDefinition = "float default null")
    private float ave_reviews;

    @Column(name = "url", nullable = false, length = 50)
    private String url;

}
