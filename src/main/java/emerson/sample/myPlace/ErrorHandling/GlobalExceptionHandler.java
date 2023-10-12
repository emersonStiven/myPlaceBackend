package emerson.sample.myPlace.ErrorHandling;

import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<?> handleException(IndexOutOfBoundsException exc){
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
    @ExceptionHandler(ApiResponseFieldEmpty.class)
    public ResponseEntity<?> handleException(ApiResponseFieldEmpty exc){
        return  ResponseEntity.status(500).body(exc.getMessage());

    }
    @ExceptionHandler(UnsatisfiedDependencyException.class)
    public ResponseEntity<?> handleException(UnsatisfiedDependencyException exc){
        return ResponseEntity.status(500).body(exc.getMessage());
    }
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<?> handleException(EmailAlreadyExistException exc){
        return ResponseEntity.status(407).body(exc.getMessage());
    }

}
