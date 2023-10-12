package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "paid_services",
        indexes = @Index(name = "idx_listing_id", columnList = "listing_id"))
public class PaidServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Listing.class,
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            optional = false)
    @JoinColumn(name = "listing_id",//join column is a type column annotation
            referencedColumnName = "listing_id",
            foreignKey = @ForeignKey(name = "frgKey_service_listing"),
            nullable = false)
    private Listing listing_id;

    @Column(name = "service_name", nullable = false, length = 100)
    private String service_name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // Getters and Setters
}
