package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Courses;
import com.Vansh.Online.Learning.App.Model.Enrollment;
import com.Vansh.Online.Learning.App.Repository.CourseRepository;
import com.Vansh.Online.Learning.App.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearnerService {
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    public ResponseEntity<?> serviceGetAllCourses() {
        try{
            List<Courses> result = courseRepository.findAll();
            if(result.isEmpty()){
                return ResponseEntity.ok("No Courses Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while fetching the Courses");
        }

    }

    public ResponseEntity<?> serviceSearchCourseByKeyword(String keyword) {
        try{
            List<Courses>result = courseRepository.findByKeyword(keyword);
            if(result.isEmpty()){
                return ResponseEntity.ok("No course Available for Your Search");
            }
            return ResponseEntity.ok(result);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while searching the Courses");
        }
    }


    public ResponseEntity<?> serviceGetAllMyCourses(String learnerusername) {
        try{
            List<Enrollment>result = enrollmentRepository.findByLearner_LearnerUsername(learnerusername);
            if(result.isEmpty()){
                return ResponseEntity.ok("No Courses Available");
            }
            return ResponseEntity.ok(result);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while Fetching the courses");
        }
    }

    public ResponseEntity<?> serviceEnrollCourse(Enrollment enrollment) {

        try{
            enrollmentRepository.save(enrollment);
            return ResponseEntity.ok("Enrolled in the course successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while enrolling in the course");
        }
    }
}
