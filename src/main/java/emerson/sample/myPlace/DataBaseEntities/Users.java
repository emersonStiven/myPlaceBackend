package emerson.sample.myPlace.DataBaseEntities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private byte[] user_id;

    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;

    @Column(name = "phone_number", length = 20, unique = true)
    private String phone_number;

    @Column(name = "pre_phone_number", length = 20, unique = true)
    private String pre_phone_number;

    @Column(name = "password", length = 60)
    private String password;

    @Lob
    @Column(name = "profile_photo")
    private byte[] profile_photo;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "bio", length = 250)
    private String bio;

    @Column(name = "created_at", nullable = false, columnDefinition = "datetime default now()")
    private LocalDateTime created_at;

    @Column(name = "last_update", nullable = false, columnDefinition = "datetime default now()")
    private LocalDateTime last_update;

    @Column(name = "last_login")
    private LocalDateTime last_login;

    @Column(name = "verification", unique = true)
    private int verification;

    @Column(name = "location_id", unique = true)
    private int location_id;

}

