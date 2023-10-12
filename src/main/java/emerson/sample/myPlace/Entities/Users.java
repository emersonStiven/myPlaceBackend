package emerson.sample.myPlace.Entities;

import emerson.sample.myPlace.Entities.EnumClasses.Role;
import emerson.sample.myPlace.Entities.EnumClasses.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Builder
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private byte[] user_id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = true)
    private String lastName;

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


    @OneToOne(targetEntity = VefNaturalPerson.class,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            optional = false)
    @JoinColumn(name = "verification",
            referencedColumnName = "id",
            unique = true,
            foreignKey = @ForeignKey(name = "frKey_verfication_user"))
    private VefNaturalPerson verification;

    @Column(name = "location_id", unique = true)
    private int location_id;
    @Embedded
    private UserHistory userHistory;
    @OneToMany(targetEntity = Token.class)
    private List<Token> tokens;
    private void addToken(String token){
        if(!Objects.nonNull(tokens)){
            tokens = new ArrayList<>();
        }
        Token t = Token.builder().user(this)
                .token(token).expired(false)
                .revoked(false)
                .type(TokenType.Bearer).build();
        tokens.add(t);

    }
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}

