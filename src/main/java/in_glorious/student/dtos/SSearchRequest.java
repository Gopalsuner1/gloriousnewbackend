package in_glorious.student.dtos;


import in_glorious.student.models.ScholarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SSearchRequest {
   private SStudentBy student_by;
   private String value;
   private ScholarStatus scholar_status;
}
