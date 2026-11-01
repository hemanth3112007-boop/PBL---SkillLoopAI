package com.skillloop.training;

import com.skillloop.common.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final BatchRepository batchRepository;

    public CourseService(CourseRepository courseRepository, BatchRepository batchRepository) {
        this.courseRepository = courseRepository;
        this.batchRepository = batchRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
    }

    @Transactional
    public Course createCourse(TrainingDtos.CreateCourseRequest req) {
        if (courseRepository.findByCourseCode(req.getCourseCode()).isPresent()) {
            throw new IllegalArgumentException("Course with code " + req.getCourseCode() + " already exists");
        }

        Course course = new Course(
                req.getCourseCode(),
                req.getTitle(),
                req.getDescription(),
                req.getDomainSector(),
                req.getDurationWeeks(),
                req.getProviderName(),
                req.getDistrict()
        );
        return courseRepository.save(course);
    }

    public List<Batch> getBatchesByCourse(Long courseId) {
        return batchRepository.findByCourseId(courseId);
    }

    @Transactional
    public Batch createBatch(TrainingDtos.CreateBatchRequest req) {
        Course course = getCourseById(req.getCourseId());
        Batch batch = new Batch(
                req.getBatchCode(),
                course,
                req.getStartDate(),
                req.getEndDate(),
                req.getTrainerName(),
                req.getMaxCapacity()
        );
        return batchRepository.save(batch);
    }
}
