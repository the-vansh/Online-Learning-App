package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Learner;
import com.Vansh.Online.Learning.App.Model.LoginRequest;
import com.Vansh.Online.Learning.App.Repository.LearnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LearnerLoginService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public ResponseEntity<?> verfiyLearner(LoginRequest loginrequest) {
        Learner learner = learnerRepository.findById(loginrequest.getUsername()).orElse(null);
        if (learner == null || !passwordEncoder.matches(loginrequest.getPassword(), learner.getLearnerPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        String token = jwtService.generateToken(learner.getLearnerUsername(), "LEARNER");

        return ResponseEntity.ok().body(Map.of("token", token, "role", "LEARNER"));
    }
}
