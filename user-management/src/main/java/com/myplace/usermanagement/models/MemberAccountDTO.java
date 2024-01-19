package com.myplace.usermanagement.models;

import com.myplace.usermanagement.entity.EnumClasses.Role;

import java.util.Set;

public record MemberAccountDTO(
        String username,
        String firstName,
        String lastName,
        String email,
        String password,
        Set<Role> roles
){

}

