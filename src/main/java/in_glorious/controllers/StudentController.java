package in_glorious.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in_glorious.dtos.StudentSearch;
import in_glorious.models.Student;
import in_glorious.services.student.StudentService;
import jakarta.servlet.annotation.MultipartConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService service;

    @GetMapping
    public ResponseEntity<Page<Student>> getStudent(){
        return ResponseEntity.ok(service.getStudent());
    }
    @PostMapping("/search_by")
    public ResponseEntity<List<Student>> getStudentBy(@Validated @RequestBody StudentSearch search){
        List<Student> students = service.searchStudent(search);
        return ResponseEntity.ok(students);
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student new_student){
        log.info("Request Is Here");
       Student student = service.addStudent(new_student);
        return ResponseEntity.ok(student);
    }
    @PutMapping
    public ResponseEntity<Student> editStudent(Student updated_student){
        return null;
    }
    @DeleteMapping
    public void deleteStudent(String student_id){

    }
   
    @PostMapping("/upload_picture")
    public ResponseEntity<String> uploadStudentPicture(
                                            @RequestParam("file") MultipartFile picture,
                                            @RequestParam("user_id") String id) throws Exception{
         service.uploadImage(picture,id);
        return ResponseEntity.ok("ok");
    }
}
