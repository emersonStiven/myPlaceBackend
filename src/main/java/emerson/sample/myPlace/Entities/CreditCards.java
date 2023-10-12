package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import emerson.sample.myPlace.Entities.EnumClasses.CardType;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "creditcards", indexes = {@Index(name = "idx_userid", columnList ="user_id" )})
public class CreditCards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Query DB for creditcard data based on the id, Hibernate creates a second query to get userid data
    //so when creditcard obj is created, i have to access to the Users obejct as well

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false, targetEntity = Users.class)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "frgKey_user_creditC"))
    private Users user_id;

    @Column(name = "cardholder_name", nullable = false, length = 100)
    private String cardholder_name;

    @Column(name = "card_number", nullable = false, length = 16)
    private String card_number;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expiration_date;

    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type", nullable = false)
    private CardType card_type;

    @Column(name = "issuing_bank", nullable = false, length = 255)
    private String issuing_bank;

    @Column(name = "billing_address", nullable = false, length = 255)
    private String billing_address;

}
