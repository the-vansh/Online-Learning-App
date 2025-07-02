package com.Vansh.Online.Learning.App.Controller;

import com.Vansh.Online.Learning.App.Model.Chapters;
import com.Vansh.Online.Learning.App.Model.Courses;
import com.Vansh.Online.Learning.App.Service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping("/addcourse")
    public ResponseEntity<?> addCourse(@RequestBody Courses course){
        return professorService.serviceAddCourse(course);
    }

    @GetMapping("/getallcoursebyprofessor/{professorusername}")
    public ResponseEntity<?> getAllCourseByProfessor(@PathVariable String professorusername){
        return professorService.serviceGetAllCourseByProfessor(professorusername);
    }

    @PostMapping("/addchapter")
    public ResponseEntity<?> addChapter(@RequestBody Chapters chapter){
        return professorService.serviceAddChapter(chapter);
    }

    @GetMapping("/getallcoursechapter/{courseid}")
    public ResponseEntity<?> getAllChapter(@PathVariable int courseid){
        return professorService.serviceGetAllChapter(courseid);
    }

    @DeleteMapping("/deletechapter/{chapterid}")
    public ResponseEntity<?>deleteChapter(@PathVariable int chapterid){
        return professorService.serviceDeleteChapterById(chapterid);
    }


    @DeleteMapping("/deletecourse/{courseid}")
    public ResponseEntity<?> deleteCourseById(@PathVariable int courseid){
        return professorService.serviceDeleteCourseById(courseid);
    }

    @GetMapping("/getenrollmentinfo/{courseid}")
    public ResponseEntity<?>getEnrollmentInfo(@PathVariable int  courseid){
        return professorService.serviceGetEnrollmentInfo(courseid);
    }
}
