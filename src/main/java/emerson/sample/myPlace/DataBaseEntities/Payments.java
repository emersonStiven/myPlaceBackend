package emerson.sample.myPlace.DataBaseEntities;
import emerson.sample.myPlace.DataBaseEntities.EnumClasses.PaymentStatus;
import emerson.sample.myPlace.DataBaseEntities.EnumClasses.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "guest_id", nullable = false)
    private UUID guest_id;

    @Column(name = "payment_method", nullable = false)
    private int payment_method;

    @Column(name = "booking_id", nullable = false)
    private int booking_id;

    @Column(name = "host_id", nullable = false)
    private UUID host_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType payment_type;

    @Column(name = "listing_id", nullable = false)
    private int listing_id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false , columnDefinition = "default ")
    private PaymentStatus status;

    @Column(name = "payment_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal payment_amount;

    @Column(name = "currency", nullable = false, length = 10)
    private String currency;

    @Column(name = "payment_date")
    private LocalDateTime payment_date;

    // Getters and Setters
}

