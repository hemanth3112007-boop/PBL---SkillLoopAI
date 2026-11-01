package com.skillloop.training;

import com.skillloop.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TraineeService {

    private final TraineeRepository traineeRepository;

    public TraineeService(TraineeRepository traineeRepository) {
        this.traineeRepository = traineeRepository;
    }

    public List<Trainee> getAllTrainees() {
        return traineeRepository.findAll();
    }

    public Trainee getTraineeById(Long id) {
        return traineeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainee not found with id: " + id));
    }

    @Transactional
    public Trainee registerTrainee(TrainingDtos.CreateTraineeRequest req) {
        if (traineeRepository.findByTraineeCode(req.getTraineeCode()).isPresent()) {
            throw new IllegalArgumentException("Trainee with code " + req.getTraineeCode() + " already exists");
        }
        if (traineeRepository.findByEmail(req.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Trainee with email " + req.getEmail() + " already exists");
        }

        Trainee trainee = new Trainee(
                req.getTraineeCode(),
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                req.getPhone(),
                req.getDistrict(),
                req.getEducationLevel()
        );
        trainee.setDateOfBirth(req.getDateOfBirth());
        trainee.setGender(req.getGender());
        trainee.setConsentGiven(req.isConsentGiven());

        return traineeRepository.save(trainee);
    }
}
