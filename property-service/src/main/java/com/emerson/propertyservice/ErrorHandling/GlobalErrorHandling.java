package com.emerson.propertyservice.ErrorHandling;

import com.emerson.propertyservice.models.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.emerson.propertyservice.models.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalErrorHandling {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(ValidationException e){
        List<String> errors = e.getBindingResult().getAllErrors().stream()
                .map(er -> String.format("%s : %s", er.getCode(), er.getDefaultMessage()))
                .toList();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(new ErrorResponseDTO<>(e.getMessage(),  errors ));
    }
    @ExceptionHandler(DataBaseInteractionException.class)
    public ResponseEntity<ErrorResponseDTO<String>> handlerException(DataBaseInteractionException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO<>(e.getMessage(),  ""));
    }




    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleError(NotFoundException e){
        return  ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(   new ErrorResponse.Builder()
                        .withMessage(e.getMessage())
                        .withStatus(false)
                        .build()
        );
    }

    @ExceptionHandler(NoListingsFoundForHost.class)
    public ResponseEntity<ErrorResponse> handleError(NoListingsFoundForHost e){
        return null;
    }

    @ExceptionHandler(ListingNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleError(ListingNotFoundException e){
        return null;
    }
}
