package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.LoginRequest;
import com.Vansh.Online.Learning.App.Model.Professors;
import com.Vansh.Online.Learning.App.Repository.LearnerRepository;
import com.Vansh.Online.Learning.App.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProfessorLoginService {

     @Autowired
     private ProfessorRepository professorRepository;

     @Autowired
     private PasswordEncoder passwordEncoder;

     @Autowired
     private JWTService jwtService;

     public ResponseEntity<?> verifyProfessor(LoginRequest loginrequest) {
         Professors professor = professorRepository.findById(loginrequest.getUsername()).orElse(null);

         if (professor == null || !passwordEncoder.matches(loginrequest.getPassword(), professor.getProfessorPassword())) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
         }

         String token = jwtService.generateToken(professor.getProfessorUsername(), "PROFESSOR");

         return ResponseEntity.ok().body(Map.of("token", token, "role", "PROFESSOR","Username",loginrequest.getUsername()));
     }
}
