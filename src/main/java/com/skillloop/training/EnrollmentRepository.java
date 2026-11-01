package com.skillloop.training;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByTraineeId(Long traineeId);
    List<Enrollment> findByBatchId(Long batchId);
    List<Enrollment> findByBatchCourseId(Long courseId);
    List<Enrollment> findByStatus(Enrollment.EnrollmentStatus status);
}

