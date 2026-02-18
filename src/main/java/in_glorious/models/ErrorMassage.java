package in_glorious.models;

import java.time.Instant;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorMassage {
    private Instant time;
    private String massage;
    private HttpStatusCode statusCode;
}
