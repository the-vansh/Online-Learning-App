package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Learner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearnerRepository extends JpaRepository<Learner,String> {}
