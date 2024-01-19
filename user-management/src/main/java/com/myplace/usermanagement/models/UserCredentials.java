package com.myplace.usermanagement.models;

import com.myplace.usermanagement.entity.EnumClasses.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserCredentials {
    private String email;
    @Column (length = 60)
    private String password;
    @Enumerated
    @Column(name = "roles")
    private Set<Role> roles;
}
