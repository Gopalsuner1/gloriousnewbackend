package in_glorious.student.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table
@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String profileUrl;
    private String scholar;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String motherName;
    @Enumerated(EnumType.STRING)
    private StudentClasses studentClass;
    private String address;
    private String phoneNumber;
    private String gender;
    private String aadhaarNumber;
    private String nameOnAadhaar;
    private String sssId;
    private LocalDate dateOfBirth;
    private LocalDate dateOfAdmission;
    @Enumerated(EnumType.STRING)
    private StudentClasses admissionClass;
    @Enumerated(EnumType.STRING)
    private ScholarStatus scholarStatus;

}
