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

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ChapterRepository chapterRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

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
            courseRepository.deleteById(courseid);
            return ResponseEntity.ok("Course Deleted Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Course");
        }

    }

    public ResponseEntity<?> serviceDeleteChapterById(int chapterid) {
        try{
            chapterRepository.deleteById(chapterid);
            return ResponseEntity.ok("Chapter deleted Successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while Deleting the Chapter");
        }
    }

    public ResponseEntity<?> serviceGetEnrollmentInfo(int courseid) {
        try{
            List<Enrollment> result = enrollmentRepository.findByCourse_CourseId(courseid);
            return ResponseEntity.ok(result);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching Enrolled detail");
        }
    }

}
