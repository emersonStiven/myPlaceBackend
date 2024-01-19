package com.myplace.usermanagement.entity.Users;

import com.myplace.usermanagement.entity.EnumClasses.Role;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(name = "serialId", columnNames = "user_id")
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false,unique = true, updatable = false)
    private UUID userId;

    @OneToOne(
            cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE},
            targetEntity = MemberInfo.class
            )    //map is to stablish a bidirectional relationship. if this were a one-to-many relationship, the one side would have the mappedBy property, to map this Collection or field to the field in the child entity.
    @JoinColumn(name = "member_info", unique = true)
    private MemberInfo memberInfo;
    @Column(name="username", length = 100)
    private String username;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;
    @Column(name = "last_update", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime lastUpdated;
    @Column(name = "last_logout")
    private LocalDateTime lastLogout;

    @Enumerated
    @Column(name = "roles")
    private Set<Role> roles;

    public void addMemberInfo(MemberInfo memberInfo){
        if(this.memberInfo == null){
            this.memberInfo = memberInfo;
        }
    }
}


