package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "free_services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FreeServices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "listing_id", nullable = false)
    private int listing_id;

    @Column(name = "service_name", nullable = false, length = 100)
    private String service_name;

    @Column(name = "description", length = 255)
    private String description;

    // Getters and Setters
}

