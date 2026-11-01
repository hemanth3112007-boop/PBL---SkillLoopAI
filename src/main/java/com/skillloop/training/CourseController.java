package com.skillloop.training;

import com.skillloop.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Courses & Training Programmes", description = "Course management, duration, providers, and batch allocations")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @Operation(summary = "Get list of all training courses")
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        return ResponseEntity.ok(ApiResponse.ok(courseService.getAllCourses()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course details by ID")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(courseService.getCourseById(id)));
    }

    @PostMapping
    @Operation(summary = "Create a new course")
    public ResponseEntity<ApiResponse<Course>> createCourse(@Valid @RequestBody TrainingDtos.CreateCourseRequest request) {
        Course created = courseService.createCourse(request);
        return ResponseEntity.ok(ApiResponse.ok("Course created successfully", created));
    }

    @GetMapping("/{id}/batches")
    @Operation(summary = "Get batches for a specific course")
    public ResponseEntity<ApiResponse<List<Batch>>> getBatches(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(courseService.getBatchesByCourse(id)));
    }

    @PostMapping("/batches")
    @Operation(summary = "Create a new training batch")
    public ResponseEntity<ApiResponse<Batch>> createBatch(@Valid @RequestBody TrainingDtos.CreateBatchRequest request) {
        Batch created = courseService.createBatch(request);
        return ResponseEntity.ok(ApiResponse.ok("Batch created successfully", created));
    }
}

