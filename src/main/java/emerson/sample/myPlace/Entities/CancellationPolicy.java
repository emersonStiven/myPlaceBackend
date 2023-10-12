package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cancelation_policy",
        indexes = {@Index(name = "idx_country_code", columnList ="country_code")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancellationPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
