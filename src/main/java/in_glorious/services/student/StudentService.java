package in_glorious.services.student;


import java.util.List;

import org.springframework.stereotype.Service;

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

    public Student addStudent(Student newStudent){
        if(newStudent.getScholar().equals(null)) throw new StudentNotFound("Student Not Found");
        log.info("Student Not Exist With This ? Scholar",newStudent.getScholar());
         if (studentRepo.existsById(newStudent.getScholar())) throw new StudentExist("Student Already Exist");
       return studentRepo.save(newStudent);
    }

    public List<Student> searchStudent(StudentSearch search){
        List<Student> students = filterStudent(search);
        if(students.size()<0) throw new StudentNotFound("Student Not Exist ");
        return students;
    }

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
    public List<Student> getStudent(){
        List<Student> students = studentRepo.findAll();
        if(students.size()<0) throw new StudentNotFound("Student not Found");
        return students;
    }
    
}
