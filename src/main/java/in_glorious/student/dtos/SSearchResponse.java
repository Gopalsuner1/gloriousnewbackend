package in_glorious.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SSearchResponse {
    private HttpStatus status;
    private List<SStudentViewMapper> students;
    private LocalDateTime time;
}
