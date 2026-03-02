package in_glorious.student.controller;

import in_glorious.student.dtos.SSearchRequest;
import in_glorious.student.dtos.SSearchResponse;
import in_glorious.student.dtos.SStudentView;
import in_glorious.student.models.Student;
import in_glorious.student.models.StudentClasses;
import in_glorious.student.repository.StudentRepo;
import in_glorious.student.service.StudentServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in_glorious.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@SuppressWarnings("NullableProblems")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentServiceImp service;
    private final StudentRepo repo;

    @GetMapping("/all")
    public ResponseEntity<SSearchResponse> getStudent(@RequestParam int page, @RequestParam int size){
        SSearchResponse student = service.getStudent(page, size);
        return ResponseEntity.ok(student);
    }
    @PostMapping("/search")
    public ResponseEntity<SSearchResponse> getStudent(@RequestBody SSearchRequest request){
        System.out.println("request is coming");
        SSearchResponse response = service.getStudent(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{student_class}")
    public ResponseEntity<SSearchResponse> getClassStudent(@PathVariable("student_class") StudentClasses studentClass){
        SSearchResponse response = service.getClassStudents(studentClass);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @GetMapping("/detail/{student_id}")
    public ResponseEntity<Student> getFullDetail(@PathVariable("student_id") String id){
        log.info("Request is here :?"+id);
        Student student = service.getStudent(id);
        return new ResponseEntity<>(student , HttpStatus.OK);
    }
    @PatchMapping
    public ResponseEntity<?> editeStudent(@RequestBody Student newStudent){
        System.out.println("request is comming for update");
        System.out.println(newStudent.toString());
        service.editStudent(newStudent);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<Student> addStudent(Student newStudent){
       Student student = service.addStudent(newStudent);
       return new ResponseEntity<Student>(student,HttpStatus.OK);
    }
    @GetMapping("/total")
    public ResponseEntity<Map<String,Object>> getNumberOfstudent(){
        Map<String,Object> map = Map.of("status",HttpStatus.OK,"value",repo.count(),"time", LocalDateTime.now());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
    @DeleteMapping
    public void deleteStudent(){

    }
    @PutMapping("/profile")
    public ResponseEntity<?> uploadProfile(@RequestParam("file")MultipartFile profile, @RequestParam("student_id") String id) throws Exception {
        service.updatePicture(profile,id);
        return new ResponseEntity<>("Profile Uploaded",HttpStatus.OK);
    }
}
