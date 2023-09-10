package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenants {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false)
    private byte[] user_id;

    @Column(name = "average_rating")
    private float average_rating;

    @Column(name = "bookings_ctn", columnDefinition = "int default 0")
    private int bookings_ctn;

    @Column(name = "payment_methods", columnDefinition = "int default 0")
    private int payment_methods;

}
