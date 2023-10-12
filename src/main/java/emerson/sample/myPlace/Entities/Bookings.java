package emerson.sample.myPlace.Entities;
import emerson.sample.myPlace.Entities.EnumClasses.BookingStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table( name = "bookings",
        indexes = {@Index(name = "idx_user", columnList = "user_id"),
                   @Index(name = "idx_listing", columnList = "listing_id")},
        uniqueConstraints = {@UniqueConstraint(name = "uniq_paymentId",columnNames = "payment_id")})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int booking_id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "frgKey_listing_user"))
    private Users user_id;

    @ManyToOne(optional = false, targetEntity = Listing.class)
    @JoinColumn(name = "listing_id",
            referencedColumnName = "listing_id",
            foreignKey = @ForeignKey(name = "frgKey_listing_booking"))
    private Listing listing_id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "frgKey_listing_payment"))
    private Payments payment_id;

    @Column(name = "check_in_date", nullable = false)
    private LocalDateTime check_in_date;

    @Column(name = "check_out_date", nullable = false)
    private LocalDateTime check_out_date;

    @Column(name = "total_guests", nullable = false)
    private int total_guests;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "cancellation_policy", nullable = false)
    private CancellationPolicy cancellation_policy;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status", nullable = false)
    private BookingStatus booking_status = BookingStatus.PENDING;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime booking_date = LocalDateTime.now();

    @Column(name = "last_update_date", nullable = false)
    private LocalDateTime last_update_date;

}

