package emerson.sample.myPlace.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import emerson.sample.myPlace.Entities.EnumClasses.HostLevel;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hosts",
        indexes = {@Index(name = "idx_user_id", columnList = "user_id")},
        uniqueConstraints = {@UniqueConstraint(name = "uniq_userid",columnNames = "user_id"),
                             @UniqueConstraint(name = "payment_method", columnNames = "payment_method")})
public class Hosts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(targetEntity = Users.class, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id",
            updatable = false,
            foreignKey = @ForeignKey(name = "frgKey_user_host"))
    private Users user_id;

    @Enumerated(value =  EnumType.STRING)
    @Column(name = "host_level", nullable = false)
    private HostLevel host_level;

    @Column(name = "average_rating")
    private Float average_rating;

    @Column(name = "listings_ctn", columnDefinition = "int default 0")
    private int listings_ctn;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime created_at;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payment_method", unique = true)
    private Payments payment_method;

}
