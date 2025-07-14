package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Chapters;
import com.Vansh.Online.Learning.App.Model.Courses;
import com.Vansh.Online.Learning.App.Model.Enrollment;
import com.Vansh.Online.Learning.App.Repository.ChapterRepository;
import com.Vansh.Online.Learning.App.Repository.CourseRepository;
import com.Vansh.Online.Learning.App.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProfessorService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public ResponseEntity<?> serviceAddCourse(Courses course) {
        try{
            courseRepository.save(course);
            return ResponseEntity.ok("Course Add Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Adding the Course");
        }
    }

    public ResponseEntity<?> serviceGetAllCourseByProfessor(String professorusername) {
        try{
           List<Courses> result = courseRepository.findByProfessor_ProfessorUsername(professorusername);
           if(result.isEmpty()){
               return ResponseEntity.ok("No Courses Available");
           }
           return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching the Course");
        }
    }

    public ResponseEntity<?> serviceAddChapter(Chapters chapter) {
        try{
            chapterRepository.save(chapter);
            return ResponseEntity.ok("Chapter Add Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding the Chapter");
        }
    }

    public ResponseEntity<?> serviceGetAllChapter(int courseid) {
        try{
            List<Chapters> result = chapterRepository.findByCourse_CourseId(courseid);
            if(result.isEmpty()){
                ResponseEntity.ok("No Chapters Available");
            }
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching the Chapter");
        }
    }

    public ResponseEntity<?> serviceDeleteCourseById(int courseid) {
        try{
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
            return ResponseEntity.ok("Course Deleted Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Course");
        }

    }

    public ResponseEntity<?> serviceDeleteChapterById(int chapterid) {
        try{
            Chapters chapter = chapterRepository.findById(chapterid).orElseThrow(()-> new RuntimeException("Chapter Not Found"));
            if(chapter.getChapterPublicId()!=null && !chapter.getChapterPublicId().isEmpty()){
                String result = cloudinaryService.deleteFile(chapter.getChapterPublicId(),"video");
                System.out.println("Cloudinary Chapter result: " + result);
            }
            chapterRepository.deleteById(chapterid);
            return ResponseEntity.ok("Chapter deleted Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Chapter");
        }
    }

    public ResponseEntity<?> serviceGetEnrollmentInfo(String professorUserName) {
        try{
            List<Courses> courses = courseRepository.findByProfessor_ProfessorUsername(professorUserName);
            List<Enrollment> allEnrollments = new ArrayList<>(); // To store all enrollments
            for (Courses course : courses) {
                List<Enrollment> result = enrollmentRepository.findByCourse_CourseId(course.getCourseId());
                allEnrollments.addAll(result);
            }
            return ResponseEntity.ok(allEnrollments);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching Enrolled detail");
        }
    }

    public ResponseEntity<?> getAllStatsInfo(String professorUserName) {
        try{
            Map<String, Object> dashboardData = new HashMap<>();
            List<Courses> courses = courseRepository.findByProfessor_ProfessorUsername(professorUserName);
            int totalCourses = courses.size();
            dashboardData.put("totalCourses", totalCourses);

            int totalEnrolledStudents = 0;
            Map<String, Integer> courseEnrollmentMap = new HashMap<>(); // For top 5 courses later

            for (Courses course : courses) {
                int enrollmentCount = enrollmentRepository.countEnrollmentsByCourseId(course.getCourseId());
                totalEnrolledStudents += enrollmentCount;
                courseEnrollmentMap.put(course.getCourseName(), enrollmentCount);
            }
            dashboardData.put("totalEnrolledStudents", totalEnrolledStudents);

            // 3️⃣ Find top 5 courses with maximum enrolled students
            List<Map.Entry<String, Integer>> sortedCourses = courseEnrollmentMap.entrySet().stream()
                    .sorted((a, b) -> b.getValue().compareTo(a.getValue())) // Sort descending
                    .limit(5) // Top 5
                    .toList();

            dashboardData.put("top5Courses", sortedCourses);

            return ResponseEntity.ok(dashboardData);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching the stats of the Users");
        }
    }
}
