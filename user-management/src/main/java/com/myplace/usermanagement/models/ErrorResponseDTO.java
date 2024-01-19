package com.myplace.usermanagement.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDTO<T> {
    public ErrorResponseDTO(String msg, boolean ok, T data) {
        this.msg = msg;
        this.ok = ok;
        this.data = data;
    }
    private String msg;
    private boolean ok;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;
}
