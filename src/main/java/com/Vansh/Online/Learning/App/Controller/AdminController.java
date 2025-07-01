package com.Vansh.Online.Learning.App.Controller;

import com.Vansh.Online.Learning.App.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/getactiveprofessors")
    public ResponseEntity<?> getActiveProfessors (){
       return adminService.findProfessorsbyStatus("Approved");
    }

    @GetMapping("/getinactiveprofessors")
    public ResponseEntity<?>getInactiveProfessors(){
        return adminService.findProfessorsbyStatus("Not Approved");
    }

    @PutMapping("/verifyprofessor/{professorUsername}")
    public ResponseEntity<?>verifyInactiveProfessor(@PathVariable String professorUsername){
        System.out.println("verify method");
        return adminService.verifyProfessor(professorUsername);
    }

    @GetMapping("/getalllearners")
    public ResponseEntity<?>getAllLearner(){
        return adminService.findAllLearner();
    }

    @GetMapping("/getallcourses")
    public ResponseEntity<?> getAllCourse(){
        return adminService.findAllCourse();
    }

    @GetMapping("/searchcoursebykeyword/{keyword}")
    public ResponseEntity<?> findProfessorCourse(@PathVariable String keyword){
        return adminService.findCourseByKeyword(keyword);
    }

    @GetMapping("/allenrolledstudent")
    public ResponseEntity<?> allEnrolledStudent(){
        return adminService.findAllEnrolledStudent();
    }

    @DeleteMapping("/deletelearner/{learnerusername}")
    public ResponseEntity<?>deleteLearner(@PathVariable String learnerusername){
        return adminService.deleteLearnerByUsername(learnerusername);
    }

    @DeleteMapping("/deleteprofessor/{professorusername}")
    public ResponseEntity<?>deleteProfessor(@PathVariable String professorusername){
        return adminService.deleteProfessorByUsername(professorusername);
    }

    @DeleteMapping("/deletecourse/{courseid}")
    public ResponseEntity<?>deleteCourse(@PathVariable int courseid){
        return adminService.deleteCourseByCourseId(courseid);
    }

}
