package emerson.sample.myPlace.DataBaseEntities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "debitcards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitCards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id", nullable = false)
    private byte[] user_id;

    @Column(name = "cardholder_name", nullable = false, length = 100)
    private String cardholder_name;

    @Column(name = "card_number", nullable = false, length = 16)
    private String card_number;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expiration_date;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Column(name = "issuing_bank", nullable = false, length = 255)
    private String issuing_bank;

    @Column(name = "account_number", nullable = false, length = 20)
    private String account_number;

    @Column(name = "billing_address", nullable = false, length = 255)
    private String billing_address;

}
