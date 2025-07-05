package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Learner;
import com.Vansh.Online.Learning.App.Model.Professors;

import com.Vansh.Online.Learning.App.Repository.LearnerRepository;
import com.Vansh.Online.Learning.App.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public ResponseEntity<?> SaveNewLearner(Learner learner) {
        if (learnerRepository.existsByLearnerUsername(learner.getLearnerUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Learner with this username already exists!");
        }
        learner.setLearnerPassword(new BCryptPasswordEncoder(12).encode(learner.getLearnerPassword()));
        learnerRepository.save(learner);
        return ResponseEntity.ok("Learner registered successfully! Email-ID: " + learner.getLearnerUsername());
    }

    public ResponseEntity<?> SaveNewProfessor(Professors professor) {
        if (professorRepository.existsByProfessorUsername(professor.getProfessorUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Professor with this username already exists!");
        }
        professor.setProfessorPassword(new BCryptPasswordEncoder(12).encode(professor.getProfessorPassword()));
        professorRepository.save(professor);
        return ResponseEntity.ok("Professor registered successfully! Email-ID: " + professor.getProfessorUsername());
    }
}

