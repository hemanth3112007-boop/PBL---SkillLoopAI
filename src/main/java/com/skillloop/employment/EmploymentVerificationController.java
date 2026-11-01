package com.skillloop.employment;

import com.skillloop.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
@Tag(name = "Employment Verification", description = "Verify employment evidence and display data-quality confidence ratings")
public class EmploymentVerificationController {

    private final EmploymentVerificationService verificationService;

    public EmploymentVerificationController(EmploymentVerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping
    @Operation(summary = "Get all verification records")
    public ResponseEntity<ApiResponse<List<EmploymentVerification>>> getAllVerifications() {
        return ResponseEntity.ok(ApiResponse.ok(verificationService.getAllVerifications()));
    }

    @GetMapping("/outcome/{outcomeId}")
    @Operation(summary = "Get verifications for a specific employment outcome")
    public ResponseEntity<ApiResponse<List<EmploymentVerification>>> getByOutcome(@PathVariable Long outcomeId) {
        return ResponseEntity.ok(ApiResponse.ok(verificationService.getVerificationsByOutcome(outcomeId)));
    }

    @GetMapping("/pending")
    @Operation(summary = "Get all pending verification reviews")
    public ResponseEntity<ApiResponse<List<EmploymentVerification>>> getPending() {
        return ResponseEntity.ok(ApiResponse.ok(verificationService.getPendingVerifications()));
    }

    @GetMapping("/conflicts")
    @Operation(summary = "Get all conflicting employment records flagged for audit")
    public ResponseEntity<ApiResponse<List<EmploymentVerification>>> getConflicts() {
        return ResponseEntity.ok(ApiResponse.ok(verificationService.getConflictVerifications()));
    }

    @PostMapping("/outcome/{outcomeId}")
    @Operation(summary = "Submit employment verification evidence (offer letter, payslip, employer confirmation)")
    public ResponseEntity<ApiResponse<EmploymentVerification>> submitEvidence(
            @PathVariable Long outcomeId,
            @Valid @RequestBody EmploymentDtos.SubmitEvidenceRequest request) {
        EmploymentVerification saved = verificationService.submitEvidence(outcomeId, request);
        return ResponseEntity.ok(ApiResponse.ok("Verification evidence submitted", saved));
    }

    @PutMapping("/{id}/review")
    @Operation(summary = "Review and approve/flag an employment verification record")
    public ResponseEntity<ApiResponse<EmploymentVerification>> review(
            @PathVariable Long id,
            @Valid @RequestBody EmploymentDtos.ReviewVerificationRequest request) {
        EmploymentVerification reviewed = verificationService.reviewVerification(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Verification reviewed successfully", reviewed));
    }
}

