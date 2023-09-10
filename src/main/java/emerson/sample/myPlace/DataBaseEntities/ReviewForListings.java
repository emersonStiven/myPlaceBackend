package emerson.sample.myPlace.DataBaseEntities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review_for_listings",
        indexes = {
        @Index(name = "idx_listingId",columnList = "listing_id"),
        @Index(name = "idx_guest_id", columnList = "guest_id")
} )
public class ReviewForListings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_id")
    private int review_id;

    @Column(name = "listing_id", nullable = false)
    private int listing_id;

    @Column(name = "guest_id", nullable = false)
    private UUID guest_id;

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

    @Column(name = "host_id", nullable = false)
    private int host_id;


}
