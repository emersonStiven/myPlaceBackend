package com.myplace.usermanagement.models;

import lombok.Data;

@Data
public class PasswordModelDTO {

    private String email;
    private String oldPassword;
    private String newPassword;
}