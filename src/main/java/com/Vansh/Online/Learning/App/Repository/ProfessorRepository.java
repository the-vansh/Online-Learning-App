package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Professors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professors,String> {}
