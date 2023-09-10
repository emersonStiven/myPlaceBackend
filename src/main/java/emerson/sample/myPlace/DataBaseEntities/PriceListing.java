package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "price_listing",
        indexes = {@Index(name = "idx_price_night", columnList = "price_night")}
)
public class PriceListing {

    @Id
    private int price_id;

    @Column(name = "price_night", nullable = false)
    private BigDecimal price_night;

    @Column(name = "price_week", nullable = false)
    private BigDecimal price_week;

    @Column(name = "price_month", nullable = false)
    private BigDecimal price_month;

    @Column(name = "cleaning_fee", nullable = false)
    private BigDecimal cleaning_fee;


}
