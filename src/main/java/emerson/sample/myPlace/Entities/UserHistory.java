package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHistory {
    @Column(name = "average_rating")
    private float average_rating;
    @Column(name = "bookings_ctn", columnDefinition = "int default 0")
    private int bookings_ctn;
    @Column(name = "payment_methods", columnDefinition = "int default 0")
    private int payment_methods;

}
