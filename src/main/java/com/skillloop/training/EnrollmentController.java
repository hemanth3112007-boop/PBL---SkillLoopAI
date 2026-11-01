package com.skillloop.training;

import com.skillloop.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@Tag(name = "Enrollments & Certifications", description = "Course enrolments, attendance, assessment results, and certifications")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    @Operation(summary = "Get all enrollment records")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollments() {
        return ResponseEntity.ok(ApiResponse.ok(enrollmentService.getAllEnrollments()));
    }

    @GetMapping("/trainee/{traineeId}")
    @Operation(summary = "Get enrolments for a specific trainee")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getByTrainee(@PathVariable Long traineeId) {
        return ResponseEntity.ok(ApiResponse.ok(enrollmentService.getEnrollmentsByTrainee(traineeId)));
    }

    @GetMapping("/batch/{batchId}")
    @Operation(summary = "Get enrolments for a specific batch")
    public ResponseEntity<ApiResponse<List<Enrollment>>> getByBatch(@PathVariable Long batchId) {
        return ResponseEntity.ok(ApiResponse.ok(enrollmentService.getEnrollmentsByBatch(batchId)));
    }

    @PostMapping
    @Operation(summary = "Enrol a trainee in a training batch")
    public ResponseEntity<ApiResponse<Enrollment>> enrollTrainee(@Valid @RequestBody TrainingDtos.EnrollTraineeRequest request) {
        Enrollment created = enrollmentService.enrollTrainee(request);
        return ResponseEntity.ok(ApiResponse.ok("Trainee enrolled successfully", created));
    }
}

