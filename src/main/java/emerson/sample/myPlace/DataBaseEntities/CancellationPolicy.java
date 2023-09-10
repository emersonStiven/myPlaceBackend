package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "cancelation_policy")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancellationPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cancellation_policy_id")
    private int cancellation_policy_id;

    @Column(name = "policy_type", nullable = false, length = 50)
    private String policy_type;

    @Column(name = "cancellation_timeline", nullable = false)
    private int cancellation_timeline;

    @Column(name = "lenguage_code", nullable = false, length = 10)
    private String lenguage_code;

    @Column(name = "country_code", nullable = false, length = 10)
    private String country_code;

}
