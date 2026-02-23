package in_glorious.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in_glorious.models.ScholarStatus;
import in_glorious.models.Student;


@Repository
public interface StudentRepo extends JpaRepository<Student,String>{
    @Query("SELECT s FROM Student s WHERE s.scholar = :scholar")
    Student findByScholar(String scholar);
List<Student> findByFirstNameContainingIgnoreCase(String keyword);
List<Student> findByFirstNameStartingWithIgnoreCaseAndScholarStatus(
        String keyword,
        ScholarStatus scholarStatus
);
@Query("""
   SELECT s FROM Student s
   WHERE LOWER(s.firstName) LIKE LOWER(CONCAT(:first, '%'))
     AND LOWER(s.lastName)  LIKE LOWER(CONCAT(:last, '%'))
     AND s.scholarStatus = :status
""")
List<Student> searchByFullName(
    @Param("first") String first,
    @Param("last") String last,
    @Param("status") ScholarStatus status
);
List<Student> findByLastNameContainingIgnoreCase(String keyword);

List<Student> findByFatherNameContainingIgnoreCase(String keyword);
boolean existsByScholar(String scholar);
Page<Student> findAll(Pageable pageable);
}