package emerson.sample.myPlace.Entities;

import emerson.sample.myPlace.Entities.EnumClasses.TokenType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "creation_date",nullable = false,updatable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;
    private TokenType type;
    @Column(name = "token")
    private String token;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "revoked")
    private boolean revoked;
    @Column(name = "expired")
    private boolean expired;
    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id",
            referencedColumnName = "user_id",
            updatable = false,nullable = false,
            foreignKey = @ForeignKey(name = ""))
    private Users user;

}
