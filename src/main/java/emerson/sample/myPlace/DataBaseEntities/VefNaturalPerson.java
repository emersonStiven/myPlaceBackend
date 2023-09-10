package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "vef_natural_person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VefNaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int user_id;

    @Column(name = "identification_number", nullable = false)
    private int identification_number;

    @Lob
    @Column(name = "proof_of_identity", nullable = false)
    private byte[] proof_of_identity;

    @Column(name = "full_name", nullable = false, length = 50)
    private String full_name;

    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;

    @Column(name = "nationality", nullable = false, length = 50)
    private String nationality;

    @Column(name = "gender", length = 50)
    private String gender;

    @Column(name = "verification_status", nullable = false)
    private String verification_status;

    @Column(name = "verified_date", nullable = true)
    private LocalDateTime verified_date;

}
