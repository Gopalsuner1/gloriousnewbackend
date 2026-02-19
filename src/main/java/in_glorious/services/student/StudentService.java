package in_glorious.services.student;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in_glorious.dtos.StudentSearch;
import in_glorious.exceptions.StudentExist;
import in_glorious.exceptions.StudentNotFound;
import in_glorious.models.Constaints;
import in_glorious.models.Student;
import in_glorious.repositories.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {
    private final StudentRepo studentRepo;
    private final CloudinaryService cloudinaryService;
    //Add New Student
    public Student addStudent(Student newStudent){
        if(newStudent.getScholar().equals(null)) throw new StudentNotFound("Student Not Found");
        log.info("Student Not Exist With This ? Scholar",newStudent.getScholar());
         if (studentRepo.existsById(newStudent.getScholar())) throw new StudentExist("Student Already Exist");
       return studentRepo.save(newStudent);
    }
    //Search Students
    public List<Student> searchStudent(StudentSearch search){
        List<Student> students = filterStudent(search);
        if(students.size()<0) throw new StudentNotFound("Student Not Exist ");
        return students;
    }
    //Filter Students
    public List<Student> filterStudent(StudentSearch search){
       return switch (search.getSearch_id()) {
            case Constaints.searchByFirstName -> 
                studentRepo.findByFirstNameContainingIgnoreCaseAndScholarStatus(search.getValue(),search.getScholar_status());
            case Constaints.searchByLastName -> 
                studentRepo.findByLastNameContainingIgnoreCase(search.getValue());
            case Constaints.searchByFatherName -> 
                studentRepo.findByFatherNameContainingIgnoreCase(search.getValue());
            case Constaints.searchByScholar -> 
                List.of(studentRepo.findByScholar(search.getValue()));
            default ->  List.of();
        };
    }
    //Get All The Students
    public Page<Student> getStudent(){
        Pageable  pageable = PageRequest.of(0, 20) ;
        return studentRepo.findAll(pageable);
    }

    //Upload Student Profile Picture
    public void uploadImage(MultipartFile file, String id) throws Exception{
        Optional<Student> student = studentRepo.findById(id);
        if (student.isEmpty()) throw new StudentNotFound("Student Not Found");
        Student student1 = (Student) student.get();
        if(!student1.getProfileUrl().isBlank()) cloudinaryService.deleteImage(student1.getProfileUrl());
        String url = cloudinaryService.uploadImage(file);
        student1.setProfileUrl(url);
        studentRepo.save(student1);
    }
    
}
