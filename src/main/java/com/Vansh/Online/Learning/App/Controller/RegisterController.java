package com.Vansh.Online.Learning.App.Controller;

import com.Vansh.Online.Learning.App.Model.Learner;
import com.Vansh.Online.Learning.App.Model.Professors;
import com.Vansh.Online.Learning.App.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/registerlearner")
    public ResponseEntity<?> registerUser(@RequestBody Learner learner) {
//        try {
//            Learner learner1 = registerService.SaveNewLearner(learner);
//            return ResponseEntity.ok("Learner registered successfully!" + " Email-ID: " + learner1.getLearnerUsername());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error registering learner: " + e.getMessage());
//        }
        return registerService.SaveNewLearner(learner);
    }

    @PostMapping("/registerprofessor")
    public ResponseEntity<?> registerProfessor(@RequestBody Professors professor) {
//        try {
//            Professors professor1 = registerService.SaveNewProfessor(professor);
//            return ResponseEntity.ok("Professor registered successfully!" + "Email-ID: " + professor1.getProfessorUsername());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error registering professor: " + e.getMessage());
//        }
        return registerService.SaveNewProfessor(professor);
    }
}
