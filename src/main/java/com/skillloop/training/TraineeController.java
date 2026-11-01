package com.skillloop.training;

import com.skillloop.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainees")
@Tag(name = "Trainee Management", description = "Trainee profiles, consent-based records, and personal demographics")
public class TraineeController {

    private final TraineeService traineeService;

    public TraineeController(TraineeService traineeService) {
        this.traineeService = traineeService;
    }

    @GetMapping
    @Operation(summary = "Get list of all registered trainees")
    public ResponseEntity<ApiResponse<List<Trainee>>> getAllTrainees() {
        return ResponseEntity.ok(ApiResponse.ok(traineeService.getAllTrainees()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get trainee profile by ID")
    public ResponseEntity<ApiResponse<Trainee>> getTraineeById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(traineeService.getTraineeById(id)));
    }

    @PostMapping
    @Operation(summary = "Register a new trainee with consent record")
    public ResponseEntity<ApiResponse<Trainee>> registerTrainee(@Valid @RequestBody TrainingDtos.CreateTraineeRequest request) {
        Trainee registered = traineeService.registerTrainee(request);
        return ResponseEntity.ok(ApiResponse.ok("Trainee registered successfully", registered));
    }
}

