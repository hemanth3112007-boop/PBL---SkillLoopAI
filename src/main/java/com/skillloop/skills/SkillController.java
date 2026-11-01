package com.skillloop.skills;

import com.skillloop.common.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
@Tag(name = "Skills Catalog", description = "Manage taxonomy of industry skills and skills mapped to training courses")
public class SkillController {

    private final SkillService skillService;
    private final AiSkillExtractionService aiExtractionService;

    public SkillController(SkillService skillService, AiSkillExtractionService aiExtractionService) {
        this.skillService = skillService;
        this.aiExtractionService = aiExtractionService;
    }

    @GetMapping
    @Operation(summary = "Get list of all registered skills in the taxonomy")
    public ResponseEntity<ApiResponse<List<Skill>>> getAllSkills(@RequestParam(required = false) String search) {
        List<Skill> list = (search != null && !search.isBlank())
                ? skillService.searchSkills(search)
                : skillService.getAllSkills();
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @PostMapping
    @Operation(summary = "Register a new skill into the taxonomy")
    public ResponseEntity<ApiResponse<Skill>> createSkill(@Valid @RequestBody SkillDtos.CreateSkillRequest request) {
        Skill created = skillService.createSkill(request);
        return ResponseEntity.ok(ApiResponse.ok("Skill registered successfully", created));
    }

    @GetMapping("/course/{courseId}")
    @Operation(summary = "Get skills taught in a specific course along with proficiency levels and allocated hours")
    public ResponseEntity<ApiResponse<List<CourseSkill>>> getCourseSkills(@PathVariable Long courseId) {
        return ResponseEntity.ok(ApiResponse.ok(skillService.getCourseSkills(courseId)));
    }

    @PostMapping("/course/{courseId}")
    @Operation(summary = "Map a skill to a course curriculum")
    public ResponseEntity<ApiResponse<CourseSkill>> addSkillToCourse(
            @PathVariable Long courseId,
            @Valid @RequestBody SkillDtos.AddCourseSkillRequest request) {
        CourseSkill cs = skillService.addSkillToCourse(courseId, request);
        return ResponseEntity.ok(ApiResponse.ok("Skill mapped to course", cs));
    }

    @DeleteMapping("/course/{courseId}/skill/{skillId}")
    @Operation(summary = "Remove a skill mapping from a course")
    public ResponseEntity<ApiResponse<Void>> removeSkillFromCourse(@PathVariable Long courseId, @PathVariable Long skillId) {
        skillService.removeSkillFromCourse(courseId, skillId);
        return ResponseEntity.ok(ApiResponse.ok("Skill unmapped from course", null));
    }

    @PostMapping("/extract")
    @Operation(summary = "AI-assisted extraction of skills from raw job description or text")
    public ResponseEntity<ApiResponse<SkillDtos.ExtractSkillsResponse>> extractSkills(@Valid @RequestBody SkillDtos.ExtractSkillsRequest request) {
        List<String> extracted = aiExtractionService.extractSkills(request.getText());
        String summary = String.format("Identified %d relevant industry skills from input text", extracted.size());
        return ResponseEntity.ok(ApiResponse.ok(new SkillDtos.ExtractSkillsResponse(extracted, summary)));
    }
}

