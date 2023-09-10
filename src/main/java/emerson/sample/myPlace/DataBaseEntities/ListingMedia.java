package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "listing_media")
public class ListingMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "listing_id", nullable = false)
    private int listing_id;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @Column(name = "position", nullable = false)
    private int position;

    @Column(name = "uploaded_at")
    private LocalDateTime uploaded_at = LocalDateTime.now();

    // Getters and Setters
}
