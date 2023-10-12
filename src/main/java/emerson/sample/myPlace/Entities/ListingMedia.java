package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "listing_media",
        indexes = @Index(name = "idx_media_listing", columnList = "listing_id"))
public class ListingMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            targetEntity = Listing.class,
            optional = false)
    @JoinColumn(name = "listing_id",
            referencedColumnName = "listing_id",
            foreignKey = @ForeignKey(name = "frgKey_media_listing"))
    private Listing listing_id;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at = LocalDateTime.now();

    // Getters and Setters
}
