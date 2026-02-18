package in_glorious.dtos;


import in_glorious.models.ScholarStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentSearch {
   private byte search_id;
   @NonNull
   private String value;
   @NonNull
   private String session_id;
   @NonNull
   private ScholarStatus scholar_status;
    }
