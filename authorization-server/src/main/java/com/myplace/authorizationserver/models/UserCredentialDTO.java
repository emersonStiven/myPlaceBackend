package com.myplace.authorizationserver.models;

import com.myplace.authorizationserver.entity.Role;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
@Builder
@Data
public class UserCredentialDTO {
    private String email;
    private String password;
    private Set<Role> roles;
}
