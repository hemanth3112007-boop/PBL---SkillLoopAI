package com.skillloop.training;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class TrainingDtos {

    public static class CreateCourseRequest {
        @NotBlank(message = "Course code is required")
        private String courseCode;

        @NotBlank(message = "Course title is required")
        private String title;

        private String description;
        private String domainSector;

        @NotNull(message = "Duration in weeks is required")
        private Integer durationWeeks;

        private String providerName;
        private String district;

        public CreateCourseRequest() {}

        public String getCourseCode() {
            return courseCode;
        }

        public void setCourseCode(String courseCode) {
            this.courseCode = courseCode;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDomainSector() {
            return domainSector;
        }

        public void setDomainSector(String domainSector) {
            this.domainSector = domainSector;
        }

        public Integer getDurationWeeks() {
            return durationWeeks;
        }

        public void setDurationWeeks(Integer durationWeeks) {
            this.durationWeeks = durationWeeks;
        }

        public String getProviderName() {
            return providerName;
        }

        public void setProviderName(String providerName) {
            this.providerName = providerName;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }

    public static class CreateBatchRequest {
        @NotBlank(message = "Batch code is required")
        private String batchCode;

        @NotNull(message = "Course ID is required")
        private Long courseId;

        @NotNull(message = "Start date is required")
        private LocalDate startDate;

        @NotNull(message = "End date is required")
        private LocalDate endDate;

        private String trainerName;
        private Integer maxCapacity;

        public CreateBatchRequest() {}

        public String getBatchCode() {
            return batchCode;
        }

        public void setBatchCode(String batchCode) {
            this.batchCode = batchCode;
        }

        public Long getCourseId() {
            return courseId;
        }

        public void setCourseId(Long courseId) {
            this.courseId = courseId;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public String getTrainerName() {
            return trainerName;
        }

        public void setTrainerName(String trainerName) {
            this.trainerName = trainerName;
        }

        public Integer getMaxCapacity() {
            return maxCapacity;
        }

        public void setMaxCapacity(Integer maxCapacity) {
            this.maxCapacity = maxCapacity;
        }
    }

    public static class CreateTraineeRequest {
        @NotBlank(message = "Trainee code is required")
        private String traineeCode;

        @NotBlank(message = "First name is required")
        private String firstName;

        @NotBlank(message = "Last name is required")
        private String lastName;

        @NotBlank(message = "Email is required")
        @Email(message = "Valid email is required")
        private String email;

        @NotBlank(message = "Phone number is required")
        private String phone;

        private String district;
        private LocalDate dateOfBirth;
        private String gender;
        private String educationLevel;
        private boolean consentGiven = true;

        public CreateTraineeRequest() {}

        public String getTraineeCode() {
            return traineeCode;
        }

        public void setTraineeCode(String traineeCode) {
            this.traineeCode = traineeCode;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEducationLevel() {
            return educationLevel;
        }

        public void setEducationLevel(String educationLevel) {
            this.educationLevel = educationLevel;
        }

        public boolean isConsentGiven() {
            return consentGiven;
        }

        public void setConsentGiven(boolean consentGiven) {
            this.consentGiven = consentGiven;
        }
    }

    public static class EnrollTraineeRequest {
        @NotNull(message = "Trainee ID is required")
        private Long traineeId;

        @NotNull(message = "Batch ID is required")
        private Long batchId;

        private LocalDate enrollmentDate = LocalDate.now();

        public EnrollTraineeRequest() {}

        public Long getTraineeId() {
            return traineeId;
        }

        public void setTraineeId(Long traineeId) {
            this.traineeId = traineeId;
        }

        public Long getBatchId() {
            return batchId;
        }

        public void setBatchId(Long batchId) {
            this.batchId = batchId;
        }

        public LocalDate getEnrollmentDate() {
            return enrollmentDate;
        }

        public void setEnrollmentDate(LocalDate enrollmentDate) {
            this.enrollmentDate = enrollmentDate;
        }
    }
}

