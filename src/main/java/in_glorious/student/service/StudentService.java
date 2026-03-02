package in_glorious.student.service;

import in_glorious.student.dtos.SSearchRequest;
import in_glorious.student.dtos.SSearchResponse;
import in_glorious.student.models.Student;
import in_glorious.student.models.StudentClasses;
import org.springframework.web.multipart.MultipartFile;

public interface StudentService {
    SSearchResponse getStudent(int page,int size);
    SSearchResponse getStudent(SSearchRequest request);
    Student getStudent(String id);
    void editStudent(Student updateStudent);
    Student addStudent(Student newStudent);
    void updatePicture(MultipartFile picture, String studentId) throws Exception;
    SSearchResponse getClassStudents(StudentClasses studentClasses);
}
