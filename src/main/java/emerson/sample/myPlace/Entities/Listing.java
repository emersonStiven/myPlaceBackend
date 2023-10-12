package emerson.sample.myPlace.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "listings",
        uniqueConstraints = {@UniqueConstraint(name = "pricing_id", columnNames = "pricing_id")},
        indexes = {@Index(name = "idx_listing_property_type", columnList = "property_type"),
                   @Index(name = "idx_listing_room_type", columnList = "room_type"),
                   @Index(name = "frg_host_listing", columnList = "host_id")})

// @UniqueConstraint(name = "location_id", columnNames = "location_id")
// @Index(name = "idx_cancelation_policy", columnList = "cancellationPolicy")
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "listing_id")
    private int listing_id;

    //Relationship
    @ManyToOne(targetEntity = Users.class, cascade = CascadeType.REMOVE, optional = false)
    //Foreign key
    @JoinColumn(name = "host_id",
                referencedColumnName = "user_id", nullable = false,
                foreignKey = @ForeignKey(name = "frg_host_listing"))
    private Users host_id;

    @OneToOne(targetEntity = PriceListing.class,cascade = CascadeType.REMOVE,optional = false)
    @JoinColumn(name = "pricing_id",unique = true,nullable = false,
            referencedColumnName = "price_id",
            foreignKey = @ForeignKey(name = "frgKey_pricing_listing"))
    private PriceListing pricing_id;

    @OneToOne(mappedBy = "listing_id",
            targetEntity = LocationListing.class,
            optional = false)
    @JoinColumn(name = "location_id",
            //I can delete this foreing key, hence, it won't show up in the table as a column
            //and still get the LocationListing object automatically thanks to Hibernate, otherwise,
            // i'd have to create a second query to find this listing_id in the location_listing
            referencedColumnName = "location_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "frgKey_listing_location"))
    private LocationListing location_id;

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
    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(targetEntity = CancellationPolicy.class,cascade = CascadeType.REFRESH)
    @JoinColumn(name = "Cancellation policy", nullable = false,
            referencedColumnName = "cancellation_policy_id",
            foreignKey = @ForeignKey(name = "frgKey_listing_cancellation"))
    private CancellationPolicy cancellationPolicy;

    @Column(name = "host_rules", length = 255)
    private String host_rules;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "last_updated", nullable = false, columnDefinition = "datetime on update now()")
    private LocalDateTime last_updated;

    @ManyToOne(targetEntity = PropertyType.class,cascade =CascadeType.DETACH,optional = false)
    @JoinColumn(name = "property_type",nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "frgKey_listing_propertyT"))
    private PropertyType property_type;

    @Column(name = "room_type", nullable = false)
    private int room_type;

    @Column(name = "tax_rate", nullable = false)
    private int tax_rate;

    @Column(name = "reviews_ctn", columnDefinition = "int default 0")
    private int reviews_ctn;

    @Column(name = "ave_reviews", columnDefinition = "float default null")
    private float ave_reviews;

    @Column(name = "url", nullable = false, length = 255)
    private String url;

}
