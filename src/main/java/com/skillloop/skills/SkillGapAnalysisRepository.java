package com.skillloop.skills;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillGapAnalysisRepository extends JpaRepository<SkillGapAnalysisResult, Long> {
    List<SkillGapAnalysisResult> findByCourseId(Long courseId);
    List<SkillGapAnalysisResult> findByJobRequirementId(Long jobRequirementId);
    List<SkillGapAnalysisResult> findByMatchLevel(SkillEnums.SkillMatchLevel matchLevel);
}

