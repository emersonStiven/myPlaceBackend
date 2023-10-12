package emerson.sample.myPlace.Entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_for_listings",
        indexes = {
        @Index(name = "idx_listingId",columnList = "listing_id"),
        @Index(name = "idx_guest_id", columnList = "guest_id"),
        @Index(name = "frgKey_host_review", columnList = "host_id")
} )
public class ReviewForListings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private int review_id;

    @ManyToOne(targetEntity = Listing.class,optional = false)
    @JoinColumn(name = "listing_id",nullable = false,
            referencedColumnName = "listing_id",
            foreignKey = @ForeignKey(name = "frgKey_listing_review"))
    private Listing listing_id;


    @ManyToOne(targetEntity = Users.class,optional = false)
    @JoinColumn(name = "guest_id",nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "frgKey_guest_review"))
    private Users guest_id;

    @Column(name = "comments", length = 255)
    private String comments;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @Column(name = "language", nullable = false, length = 10)
    private String language;

    @Column(name = "cleanliness", nullable = false)
    private float cleanliness;

    @Column(name = "reliability", nullable = false)
    private float reliability;

    @Column(name = "communication", nullable = false)
    private float communication;

    @Column(name = "arrival", nullable = false)
    private float arrival;

    @Column(name = "price", nullable = false)
    private float price;


    @ManyToOne(targetEntity = Hosts.class,optional = false)
    @JoinColumn(name = "host_id",nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "frgKey_host_review"))
    private Hosts host_id;


}
