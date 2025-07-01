package com.Vansh.Online.Learning.App.Controller;
import com.Vansh.Online.Learning.App.Model.Learner;
import com.Vansh.Online.Learning.App.Model.LoginRequest;
import com.Vansh.Online.Learning.App.Model.Professors;
import com.Vansh.Online.Learning.App.Service.AdminLoginService;
import com.Vansh.Online.Learning.App.Service.LearnerLoginService;
import com.Vansh.Online.Learning.App.Service.ProfessorLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
class LoginController {

    @Autowired
    private LearnerLoginService learnerService;

    @Autowired
    private ProfessorLoginService professorService;

    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginrequest) {
        String role = loginrequest.getRole().toUpperCase();

        if (role.equals("LEARNER")) {
            return learnerService.verifyLearner(loginrequest);
        } else if (role.equals("PROFESSOR")) {
            return  professorService.verifyProfessor(loginrequest);
        }
        else if(role.equals("ADMIN")){
            return adminLoginService.verifyAdmin(loginrequest);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid role");
        }
    }
}