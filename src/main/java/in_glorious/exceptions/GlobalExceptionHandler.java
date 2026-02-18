package in_glorious.exceptions;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import in_glorious.models.ErrorMassage;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(exception = StudentNotFound.class)
    public ResponseEntity<ErrorMassage> handelStudentNotFoundEx(StudentNotFound ex){
        ErrorMassage errorMassage = ErrorMassage.builder()
                                                .time(Instant.now())
                                                .massage(ex.getMessage())
                                                .build();
        return ResponseEntity.ok(errorMassage);
    }
    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ErrorMassage> handelException(Exception ex){
        ErrorMassage errorMassage = ErrorMassage.builder()
                                                .time(Instant.now())
                                                .massage(ex.getMessage())
                                                .build();
        return ResponseEntity.ok(errorMassage);
    }
}
