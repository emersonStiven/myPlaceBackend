package emerson.sample.myPlace.Entities;
import emerson.sample.myPlace.Entities.EnumClasses.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vef_natural_person",
        uniqueConstraints = {@UniqueConstraint(name = "user_id", columnNames = "user_id")},
        indexes={@Index(name = "idx_user_id",columnList ="user_id")}
)
public class VefNaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "frgKey_user_verification"))
    private Users user_id;

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
    @Enumerated(value = EnumType.STRING)
    private VerificationStatus verification_status;

    @Column(name = "verified_date")
    private LocalDateTime verified_date;

}
