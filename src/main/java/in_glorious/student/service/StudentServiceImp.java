package in_glorious.student.service;


import in_glorious.exceptions.StudentExist;
import in_glorious.exceptions.StudentNotFound;
import in_glorious.services.CloudinaryService;
import in_glorious.student.dtos.SSearchRequest;
import in_glorious.student.dtos.SSearchResponse;
import in_glorious.student.dtos.SStudentView;
import in_glorious.student.dtos.SStudentViewMapper;
import in_glorious.student.models.ScholarStatus;
import in_glorious.student.models.StudentClasses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;
import in_glorious.student.models.Student;
import in_glorious.student.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("NullableProblems")
@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImp implements StudentService{
    private final StudentRepo studentRepo;
    private final CloudinaryService cloudinaryService;
    @Override
    public void updatePicture(MultipartFile picture, String studentId) throws Exception {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new StudentNotFound("Student not found with id: " + studentId));
        String oldUrl = student.getProfileUrl();
        if (oldUrl != null && !oldUrl.isBlank()) {
            cloudinaryService.deleteImage(oldUrl);
        }
        System.out.println("we are here ");
        String newUrl = cloudinaryService.uploadImage(picture);
        System.out.println("we have pass");
        student.setProfileUrl(newUrl);
        studentRepo.save(student);
    }

    @Override
    public SSearchResponse getClassStudents(StudentClasses studentClasses) {
        List<SStudentViewMapper> students = studentRepo
                .findAllByStudentClass(studentClasses)
                .stream()
                .map(this::sSearchMapper)
                .sorted(Comparator.comparing(SStudentViewMapper::getFirstName))
                .toList();

        return SSearchResponse.builder()
                .time(LocalDateTime.now())
                .students(students)
                .status(HttpStatus.OK)
                .build();
    }

    public SStudentViewMapper sSearchMapper(SStudentView s){
       return SStudentViewMapper.builder()
                .id(s.getId())
                .firstName(s.getFirstName())
                .lastName(s.getLastName())
                .scholar(s.getScholar())
                .fatherName(s.getFatherName())
                .phoneNumber(s.getPhoneNumber())
                .profileUrl(s.getProfileUrl())
                .studentClass(s.getStudentClass())
                .address(s.getAddress())
               .build();
    }
    @Override
    public SSearchResponse getStudent(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        System.out.println("Not Error");
        Page<SStudentView> studentViews = studentRepo.findAllBy(pageable);
        System.out.println("Error is here");
        return pageToResponse(studentViews);
    }
    public SSearchResponse pageToResponse(Page<SStudentView> views){
        List<SStudentViewMapper> students = new ArrayList<>();
        views.map(s-> students.add(sSearchMapper(s)));
        return SSearchResponse.builder()
                .time(LocalDateTime.now())
                .students(students)
                .status(HttpStatus.OK)
                .build();
    }

    @Override
    public SSearchResponse getStudent(SSearchRequest request) {
        List<SStudentViewMapper> newStudentView = filterStudent(request)
                .stream()
                .map(this::sSearchMapper)
                .sorted(Comparator.comparing(SStudentViewMapper::getFirstName))
                .toList();

        return SSearchResponse.builder()
                .status(HttpStatus.OK)
                .students(newStudentView)
                .time(LocalDateTime.now())
                .build();
    }

    public List<SStudentView> filterStudent(SSearchRequest request){
        String[] parts = request.getValue().trim().split("\\s+");
        boolean isFirstOnly = parts.length == 1;
        return switch (request.getStudent_by()){
            case FIRST -> isFirstOnly ? studentRepo
                    .findByFirstNameStartingWithIgnoreCaseAndScholarStatus(parts[0],request.getScholar_status()):
                    studentRepo.searchByFullName(parts[0],parts[1],request.getScholar_status());
            case LAST -> studentRepo.findByLastNameStartingWithIgnoreCaseAndScholarStatus(parts[0],request.getScholar_status());
            case FATHER -> isFirstOnly ? studentRepo
                    .findByFatherNameStartingWithIgnoreCaseAndScholarStatus(parts[0],request.getScholar_status()):
                    studentRepo.searchByFatherFullName(parts[0],parts[1],request.getScholar_status());
            case MOTHER -> isFirstOnly ? studentRepo
                    .findByMotherNameStartingWithIgnoreCaseAndScholarStatus(parts[0],request.getScholar_status()):
                    studentRepo.searchByMotherFullName(parts[0],parts[1],request.getScholar_status());
            case SCHOLAR -> List.of(studentRepo.findByScholar(request.getValue()));
            case NUMBER -> List.of();
            default -> throw new IllegalStateException("Unexpected value: " + request.getStudent_by());
        };
    }

    @Override
    public Student getStudent(String id) {
        return studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFound("Student not found"));
    }

    @Override
    public void editStudent(Student updateStudent) {
        if (!studentRepo.existsById(updateStudent.getId())) {
            System.out.println("error");
            throw new StudentNotFound("student not found");
        }
        System.out.println("student is ipdate"+studentRepo.save(updateStudent).toString());
    }

    @Override
    public Student addStudent(Student newStudent) {
        if (studentRepo.existsByScholar(newStudent.getScholar()))
            throw new StudentExist("Scholar already exist");
        newStudent.setDateOfAdmission(LocalDate.now());
        newStudent.setProfileUrl("");
        newStudent.setScholarStatus(ScholarStatus.REGULAR);
        return studentRepo.save(newStudent);
    }
    @GetMapping("/ok")
    public void test(){
        log.info("this request is only for active service");
    }

}
