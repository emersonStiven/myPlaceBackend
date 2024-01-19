package com.myplace.usermanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseHttp {
    String msg;
    boolean ok;
    UserCredentials data;
}
