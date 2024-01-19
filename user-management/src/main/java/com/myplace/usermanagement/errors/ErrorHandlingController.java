package com.myplace.usermanagement.errors;

import com.myplace.usermanagement.errors.ErrorTypes.DataBaseInteractionException;
import com.myplace.usermanagement.errors.ErrorTypes.ValidationException;
import com.myplace.usermanagement.models.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.List;

@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(DataBaseInteractionException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handlerException(DataBaseInteractionException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO<>(e.getMessage(), e.getOk(), ""));
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(ValidationException e){
        List<String> errors = e.getBindingResult().getAllErrors().stream()
                        .map(er -> String.format("%s : %s", er.getCode(), er.getDefaultMessage()))
                        .toList();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponseDTO<>(e.getMessage(), false, errors ));
    }
}
