package in_glorious.models;

import java.time.Instant;
import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String profileUrl = "";
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
    private String adharNumber;
    private String nameOnAdhar;
    private String sssId;
    private LocalDate dateOfBirth;
    private LocalDate dateOfAdmission;
    @Enumerated(EnumType.STRING)
    private StudentClasses admissionClass;
    private byte isRte;
    @Enumerated(EnumType.STRING)
    private ScholarStatus scholarStatus = ScholarStatus.REGULAR;

}
