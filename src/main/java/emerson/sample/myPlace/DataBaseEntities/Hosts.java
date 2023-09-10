package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import emerson.sample.myPlace.DataBaseEntities.EnumClasses.HostLevel;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hosts", indexes = {@Index(name = "idx_user_id", columnList = "user_id")})
public class Hosts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false, unique = true)
    private byte[] user_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "host_level", nullable = false)
    private HostLevel host_level;

    @Column(name = "average_rating")
    private Float average_rating;

    @Column(name = "listings_ctn", columnDefinition = "int default 0")
    private int listings_ctn;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "payment_method", unique = true)
    private int payment_method;

}
