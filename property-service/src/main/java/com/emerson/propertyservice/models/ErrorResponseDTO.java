package com.emerson.propertyservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDTO<T> {
    public ErrorResponseDTO(String msg,  T data) {
        this.msg = msg;
        this.ok = false;
        this.data = data;
    }
    private String msg;
    private boolean ok;
    private LocalDateTime timestamp = LocalDateTime.now();
    private T data;
}
