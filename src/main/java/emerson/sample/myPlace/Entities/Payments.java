package emerson.sample.myPlace.Entities;
import emerson.sample.myPlace.Entities.EnumClasses.PaymentStatus;
import emerson.sample.myPlace.Entities.EnumClasses.PaymentType;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments",indexes = {
        @Index(name = "frgKey_payment_paypal", columnList = "payment_paypal"),
        @Index(name = "frgKey_payment_debitcard", columnList = "payment_debitcard"),
        @Index(name="frgKey_payment_creditcard", columnList = "payment_creditcard"),
        @Index(name = "frgKey_guest_payment", columnList = "guest_id"),
        @Index(name = "idx_bookingId", columnList = "booking_id"),
        @Index(name = "idx_hostId", columnList = "host_id"),
        @Index(name = "frgKey_listing_payments", columnList = "listing_id")
})
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(targetEntity = Users.class,cascade = CascadeType.DETACH,optional = false)
    @JoinColumn(name = "guest_id",unique = true,nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "payments_ibfk_1"))
    private Users guest_id;

    @ManyToOne(targetEntity = Bookings.class,optional = false)
    @JoinColumn(name = "booking_id", nullable = false,
            referencedColumnName = "booking_id",
            foreignKey = @ForeignKey(name = "frgKey_booking_payments"))
    private Bookings booking_id;


    @ManyToOne(targetEntity = Hosts.class)
    @JoinColumn(name = "host_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "frgKey_host_payments"))
    private Hosts host_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType payment_type;
    @ManyToOne(targetEntity = CreditCards.class)
    @JoinColumn(name = "payment_creditcard",
            referencedColumnName = "id",
            columnDefinition = "default null",
            foreignKey = @ForeignKey(name = "frgKey_payment_creditcard"))
    private CreditCards creditCard;

    @ManyToOne(targetEntity = DebitCards.class)
    @JoinColumn(name = "payment_debitcard",
            referencedColumnName = "id",
            columnDefinition = "default null",
            foreignKey = @ForeignKey(name = "frgKey_payment_debitcard"))
    private DebitCards debitCard;

    @ManyToOne(targetEntity = PaypalAccounts.class)
    @JoinColumn(name = "payment_paypal",
            referencedColumnName = "id",
            columnDefinition = "default null",
            foreignKey = @ForeignKey(name = "frgKey_payment_paypal"))
    private PaypalAccounts paypalAccounts;

    @ManyToOne(targetEntity = Listing.class,cascade = CascadeType.DETACH,optional = false)
    @JoinColumn(name = "listing_id",
            referencedColumnName = "listing_id",
            nullable = false)
    private Listing listing_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    @Column(name = "payment_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal payment_amount;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    @Column(name = "payment_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime payment_date;

    // Getters and Setters
}

