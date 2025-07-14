package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.*;
import com.Vansh.Online.Learning.App.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private CloudinaryService cloudinaryService;

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
            Learner learner = learnerRepository.findById(learnerusername).orElseThrow(()->new RuntimeException("Learner Not Found"));
            if(learner.getLearnerPublicId()!=null && !learner.getLearnerPublicId().isEmpty()){
                String result = cloudinaryService.deleteFile(learner.getLearnerPublicId(),"image");
                System.out.println("Cloudinary deletion result: " + result);
            }
            learnerRepository.deleteById(learnerusername);
            return ResponseEntity.ok("Learner deleted Successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the learner");
        }
    }

    public ResponseEntity<?> deleteProfessorByUsername(String professorusername) {

       try {
           List<Courses> allcourse = courseRepository.findByProfessor_ProfessorUsername(professorusername);
           for(int i=0;i< allcourse.size();i++){
               List<Chapters> chaptersInCourse = chapterRepository.findByCourse_CourseId(allcourse.get(i).getCourseId());
               for(int j=0;j< chaptersInCourse.size();j++){
                   String publicId = chaptersInCourse.get(j).getChapterPublicId();
                   if(publicId!=null && !publicId.isEmpty()){
                       String result = cloudinaryService.deleteFile(publicId, "video");
                       System.out.println("Cloudinary Chapter Deleted: " + result);
                   }
               }
               String CoursePublicId = allcourse.get(i).getCoursePublicId();
               if(CoursePublicId!=null && !CoursePublicId.isEmpty()){
                   String result = cloudinaryService.deleteFile(CoursePublicId, "image");
                   System.out.println("Cloudinary Course Deleted: " + result);
               }
           }


           Professors professors = professorRepository.findById(professorusername).orElseThrow(()-> new RuntimeException("Professor Not Found"));
           if(professors.getProfessorPublicId()!=null && !professors.getProfessorPublicId().isEmpty()){
               String result = cloudinaryService.deleteFile(professors.getProfessorPublicId(),"image");
               System.out.println("Cloudinary deletion result: " + result);
           }
            professorRepository.deleteById(professorusername);
            return ResponseEntity.ok("Professor deleted Successfully");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Professor");
        }
    }

    public ResponseEntity<?> deleteCourseByCourseId(int courseid) {

            try {

                List<Chapters> chapters = chapterRepository.findByCourse_CourseId(courseid);
                for (int i = 0; i < chapters.size(); i++) {
                    String publicId = chapters.get(i).getChapterPublicId();
                    if (publicId != null && !publicId.isEmpty()) {
                        String result = cloudinaryService.deleteFile(publicId, "video");
                        System.out.println("Cloudinary Chapter Deleted: " + result);
                    }
                }

                Courses course = courseRepository.findById(courseid)
                        .orElseThrow(() -> new RuntimeException("Course not found"));

                if (course.getCoursePublicId() != null && !course.getCoursePublicId().isEmpty()) {
                    String result = cloudinaryService.deleteFile(course.getCoursePublicId(),"image");
                    System.out.println("Cloudinary deletion result: " + result);
                }

                courseRepository.deleteById(courseid);
                return ResponseEntity.ok("Course deleted Successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Course");
            }

    }

    public ResponseEntity<?> getAllDashboardDetails() {
        try{
            Map<String, Object> dashboardData = new HashMap<>();
            dashboardData.put("professorCount", professorRepository.count());
            dashboardData.put("learnerCount", learnerRepository.count());
            dashboardData.put("enrollmentCount", enrollmentRepository.count());
            dashboardData.put("courseCount", courseRepository.count());
            dashboardData.put("enrollmentsLastMonth", enrollmentRepository.countByEnrollmentDateAfter(LocalDate.now().minusMonths(1)));
            dashboardData.put("enrollmentsLastWeek", enrollmentRepository.countByEnrollmentDateAfter(LocalDate.now().minusWeeks(1)));
            dashboardData.put("enrollmentsToday", enrollmentRepository.countByEnrollmentDate(LocalDate.now()));
            List<Map<String, Object>> topCourses = courseRepository.findTop5CoursesByEnrollmentCount();
            dashboardData.put("topCourses", topCourses);

            return ResponseEntity.ok(dashboardData);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching the details");
        }
    }

}
