package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Learner;
import com.Vansh.Online.Learning.App.Model.Professors;

import com.Vansh.Online.Learning.App.Repository.LearnerRepository;
import com.Vansh.Online.Learning.App.Repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class RegisterService {

    @Autowired
    private LearnerRepository learnerRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Learner SaveNewLearner(Learner learner) {
        learner.setLearnerPassword(new BCryptPasswordEncoder(12).encode(learner.getLearnerPassword()));
        return learnerRepository.save(learner);
    }

    public Professors SaveNewProfessor(Professors professor) {
        professor.setProfessorpassword(new BCryptPasswordEncoder(12).encode(professor.getProfessorpassword()));
        return professorRepository.save(professor);
    }
}

