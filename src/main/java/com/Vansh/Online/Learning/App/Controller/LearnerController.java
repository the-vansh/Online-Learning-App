package com.Vansh.Online.Learning.App.Controller;


import com.Vansh.Online.Learning.App.Model.Enrollment;
import com.Vansh.Online.Learning.App.Service.JWTService;
import com.Vansh.Online.Learning.App.Service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learner")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @Autowired
    private JWTService jwtService;

    @GetMapping("/getallcourses")
    public ResponseEntity<?> getAllCourses(){
        return learnerService.serviceGetAllCourses();
    }

    @GetMapping("/seacrhcoursebykeyword/{keyword}")
    public ResponseEntity<?> searchCourseByKeyword(@PathVariable String keyword){
        return learnerService.serviceSearchCourseByKeyword(keyword);
    }

    @GetMapping("/getmycourses/{learnerusername}")
    public ResponseEntity<?> getMyCourses(@PathVariable String learnerusername,@RequestHeader("Authorization") String authHeader){
        String token = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;
        String jwtUsername = jwtService.extractUsername(token);
        if (!jwtUsername.equals(learnerusername)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized access: token mismatch");
        }
        return learnerService.serviceGetAllMyCourses(learnerusername);
    }

    @PostMapping("/enrollcourse")
    public ResponseEntity<?> enrollCourse(@RequestBody Enrollment enrollment){
        return learnerService.serviceEnrollCourse(enrollment);
    }

}
