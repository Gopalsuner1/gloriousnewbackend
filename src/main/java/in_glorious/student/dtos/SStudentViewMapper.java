package in_glorious.student.dtos;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import in_glorious.student.models.StudentClasses;
import lombok.Builder;
import lombok.Data;

@JsonPropertyOrder({
        "id",
        "firstName",
        "lastName",
        "fatherName",
        "studentClass",
        "scholar",
        "phoneNumber",
        "address",
        "profileUrl"
})
@Data
@Builder
public class SStudentViewMapper {
    private String id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private StudentClasses studentClass;
    private String scholar;
    private String phoneNumber;
    private String address;
    private String profileUrl;
}
