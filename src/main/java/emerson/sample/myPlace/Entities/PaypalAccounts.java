package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paypal_accounts",
        uniqueConstraints = @UniqueConstraint(name = "uniq_paypalE",columnNames = "paypal_email"),
        indexes = {@Index(name = "idx_email",columnList = "paypal_email"),
                @Index(name = "frgKey_user_paypal",columnList = "user_id")})
public class PaypalAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(targetEntity = Users.class,optional = false)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "paypal_accounts_ibfk_1"))
    private Users user_id;

    @Column(name = "paypal_email", nullable = false, length = 100)
    private String paypal_email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "last_update")
    private LocalDateTime last_update;

}
