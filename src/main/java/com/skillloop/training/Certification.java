package com.skillloop.training;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enrollment_id", nullable = false, unique = true)
    private Enrollment enrollment;

    @Column(nullable = false, unique = true, length = 60)
    private String certificateNumber;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(length = 120)
    private String verifyingAuthority;

    @Column(length = 255)
    private String verificationUrl;

    public Certification() {}

    public Certification(Enrollment enrollment, String certificateNumber, LocalDate issueDate, String verifyingAuthority, String verificationUrl) {
        this.enrollment = enrollment;
        this.certificateNumber = certificateNumber;
        this.issueDate = issueDate;
        this.verifyingAuthority = verifyingAuthority;
        this.verificationUrl = verificationUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getVerifyingAuthority() {
        return verifyingAuthority;
    }

    public void setVerifyingAuthority(String verifyingAuthority) {
        this.verifyingAuthority = verifyingAuthority;
    }

    public String getVerificationUrl() {
        return verificationUrl;
    }

    public void setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }
}

