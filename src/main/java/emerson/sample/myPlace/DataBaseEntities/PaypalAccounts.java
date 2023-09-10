package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "paypal_accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaypalAccounts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false)
    private byte[] user_id;

    @Column(name = "paypal_email", nullable = false, length = 100)
    private String paypal_email;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;

    @Column(name = "last_update")
    private LocalDateTime last_update;

}
