package emerson.sample.myPlace.DataBaseEntities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "property_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type_name", nullable = false, length = 50)
    private String type_name;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "created_at", nullable = false, columnDefinition = "datetime on update now()")
    private LocalDateTime created_at;

    @Column(name = "last_updated", nullable = false, columnDefinition = "datetime on update now()")
    private LocalDateTime last_updated;

}

