package com.skillloop.training;

import com.skillloop.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final TraineeRepository traineeRepository;
    private final BatchRepository batchRepository;

    public EnrollmentService(
            EnrollmentRepository enrollmentRepository,
            TraineeRepository traineeRepository,
            BatchRepository batchRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.traineeRepository = traineeRepository;
        this.batchRepository = batchRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public List<Enrollment> getEnrollmentsByTrainee(Long traineeId) {
        return enrollmentRepository.findByTraineeId(traineeId);
    }

    public List<Enrollment> getEnrollmentsByBatch(Long batchId) {
        return enrollmentRepository.findByBatchId(batchId);
    }

    @Transactional
    public Enrollment enrollTrainee(TrainingDtos.EnrollTraineeRequest req) {
        Trainee trainee = traineeRepository.findById(req.getTraineeId())
                .orElseThrow(() -> new ResourceNotFoundException("Trainee not found with id: " + req.getTraineeId()));

        Batch batch = batchRepository.findById(req.getBatchId())
                .orElseThrow(() -> new ResourceNotFoundException("Batch not found with id: " + req.getBatchId()));

        Enrollment enrollment = new Enrollment(trainee, batch, req.getEnrollmentDate(), Enrollment.EnrollmentStatus.ENROLLED);
        return enrollmentRepository.save(enrollment);
    }
}
