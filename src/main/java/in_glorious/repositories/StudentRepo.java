package in_glorious.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in_glorious.models.ScholarStatus;
import in_glorious.models.Student;


@Repository
public interface StudentRepo extends JpaRepository<Student,String>{
    Student findByScholar(String scholar);
List<Student> findByFirstNameContainingIgnoreCase(String keyword);
List<Student> findByFirstNameContainingIgnoreCaseAndScholarStatus(
        String keyword,
        ScholarStatus scholarStatus
);
List<Student> findByLastNameContainingIgnoreCase(String keyword);

List<Student> findByFatherNameContainingIgnoreCase(String keyword);
    boolean existsByScholar(String scholar);
}