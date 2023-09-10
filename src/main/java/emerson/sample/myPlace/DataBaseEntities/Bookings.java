package emerson.sample.myPlace.DataBaseEntities;
import emerson.sample.myPlace.DataBaseEntities.EnumClasses.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private int booking_id;

    @Column(name = "user_id", nullable = false)
    private UUID user_id;

    @Column(name = "listing_id", nullable = false)
    private int listing_id;

    @Column(name = "payment_id", nullable = false)
    private int payment_id;

    @Column(name = "check_in_date", nullable = false)
    private LocalDateTime check_in_date;

    @Column(name = "check_out_date", nullable = false)
    private LocalDateTime check_out_date;

    @Column(name = "total_guests", nullable = false)
    private int total_guests;

    @Column(name = "cancellation_policy", nullable = false)
    private int cancellation_policy;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus booking_status = BookingStatus.PENDING;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime booking_date = LocalDateTime.now();

    @Column(name = "last_update_date", nullable = false)
    private LocalDateTime last_update_date;

    @Column(name = "unique key uniq_paymentId", unique = true, nullable = false)
    private int paymentId;


}

