package in_glorious.student.dtos;


import in_glorious.student.models.StudentClasses;
import in_glorious.student.service.StudentService;

public interface SStudentView {
    String getId();
    String getFirstName();
    String getLastName();
    String getFatherName();
    StudentClasses getStudentClass();
    String getScholar();
    String getProfileUrl();
    String getAddress();
    String getPhoneNumber();
}
