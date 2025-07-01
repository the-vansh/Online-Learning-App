package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Courses;
import com.Vansh.Online.Learning.App.Model.Enrollment;
import com.Vansh.Online.Learning.App.Model.Learner;
import com.Vansh.Online.Learning.App.Model.Professors;
import com.Vansh.Online.Learning.App.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    LearnerRepository learnerRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    ChapterRepository chapterRepository;

    public ResponseEntity<?> findProfessorsbyStatus(String status) {
        try {
            List<Professors> result = professorRepository.findByStatus(status);
            if (result.isEmpty() && status.equals("Approved")) {
                return ResponseEntity.ok("No Approved Professors Available");
            }
            else if (result.isEmpty() && status.equals("Not Approved")) {
                return ResponseEntity.ok("No Not Approved Professors Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching active professors");
        }
    }

    public ResponseEntity<?> verifyProfessor(String professorUsername) {
        try{
            Optional<Professors> professorsOptional = professorRepository.findById(professorUsername);
            if (professorsOptional.isPresent()) {
                Professors professor = professorsOptional.get();
                professor.setStatus("Approved"); // update status
                professorRepository.save(professor); // save updated entity
                return ResponseEntity.ok("Professor verified and status updated to Approved");

            } else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Professor with username " + professorUsername + " not found");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while verify the Professor");
        }
    }

    public ResponseEntity<?> findAllLearner() {
        try {
            List<Learner> result = learnerRepository.findAll();
            if (result.isEmpty()) {
                return ResponseEntity.ok("No Learner Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching learner");
        }
    }

    public ResponseEntity<?> findAllCourse() {
        try {
            List<Courses> result = courseRepository.findAll();
            if (result.isEmpty()) {
                return ResponseEntity.ok("No Courses Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching Courses");
        }
    }

    public ResponseEntity<?> findCourseByKeyword(String keyword) {
        try {
            List<Courses> result = courseRepository.findByKeyword(keyword);
            if (result.isEmpty()) {
                return ResponseEntity.ok("No Courses Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching Courses");
        }
    }

    public ResponseEntity<?> findAllEnrolledStudent() {
        try {
            List<Enrollment> result = enrollmentRepository.findAll();
            if (result.isEmpty()) {
                return ResponseEntity.ok("No Students Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching Enrolled Student Data");
        }
    }

    public ResponseEntity<?> deleteLearnerByUsername(String learnerusername) {
        try {
            learnerRepository.deleteById(learnerusername);
            return ResponseEntity.ok("Learner deleted Successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the learner");
        }
    }

    public ResponseEntity<?> deleteProfessorByUsername(String professorusername) {
        try {
            professorRepository.deleteById(professorusername);
            return ResponseEntity.ok("Professor deleted Successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Professor");
        }
    }

    public ResponseEntity<?> deleteCourseByCourseId(int courseid) {

            try {
                courseRepository.deleteById(courseid);
                return ResponseEntity.ok("Course deleted Successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Course");
            }

    }
}
