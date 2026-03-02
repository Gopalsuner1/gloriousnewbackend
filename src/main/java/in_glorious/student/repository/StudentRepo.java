package in_glorious.student.repository;

import java.util.List;
import in_glorious.student.dtos.SStudentView;
import in_glorious.student.models.ScholarStatus;
import in_glorious.student.models.StudentClasses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import in_glorious.student.models.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student,String>{

SStudentView findByScholar(String scholar);
boolean existsByScholar(String s);
List<SStudentView> findAllByStudentClass(StudentClasses studentClasses);
@SuppressWarnings("NullableProblems")
@Query("SELECT s FROM Student s")
Page<SStudentView> findAllBy(Pageable pageable);
List<SStudentView> findByFirstNameStartingWithIgnoreCaseAndScholarStatus(
        String keyword,
        ScholarStatus scholarStatus
);
@Query("""
   SELECT s FROM Student s
   WHERE LOWER(s.firstName) LIKE LOWER(CONCAT(:first, '%'))
     AND LOWER(s.lastName)  LIKE LOWER(CONCAT(:last, '%'))
     AND s.scholarStatus = :status
""")
List<SStudentView> searchByFullName(
            @Param("first") String first,
            @Param("last") String last,
            @Param("status") ScholarStatus status);

List<SStudentView> findByLastNameStartingWithIgnoreCaseAndScholarStatus(
        String keyword,
        ScholarStatus scholarStatus);
@Query("""
   SELECT s FROM Student s
   WHERE LOWER(s.lastName) LIKE LOWER(CONCAT(:first, '%'))
     AND LOWER(s.lastName)  LIKE LOWER(CONCAT(:last, '%'))
     AND s.scholarStatus = :status
""")
List<SStudentView> searchByLastFullName(
            @Param("first") String lastName,
            @Param("last") String last,
            @Param("status") ScholarStatus status);
List<SStudentView> findByFatherNameStartingWithIgnoreCaseAndScholarStatus(
        String keyword,
        ScholarStatus scholarStatus);
@Query("""
   SELECT s FROM Student s
   WHERE LOWER(s.fatherName) LIKE LOWER(CONCAT(:first, '%'))
     AND LOWER(s.lastName)  LIKE LOWER(CONCAT(:last, '%'))
     AND s.scholarStatus = :status
""")
List<SStudentView> searchByFatherFullName(
            @Param("first") String fatherName,
            @Param("last") String last,
            @Param("status") ScholarStatus status);

List<SStudentView> findByMotherNameStartingWithIgnoreCaseAndScholarStatus(
        String keyword,
        ScholarStatus scholarStatus);

@Query("""
   SELECT s FROM Student s
   WHERE LOWER(s.motherName) LIKE LOWER(CONCAT(:first, '%'))
     AND LOWER(s.lastName)  LIKE LOWER(CONCAT(:last, '%'))
     AND s.scholarStatus = :status
""")
List<SStudentView> searchByMotherFullName(
            @Param("first") String motherName,
            @Param("last") String last,
            @Param("status") ScholarStatus status
    );
}
