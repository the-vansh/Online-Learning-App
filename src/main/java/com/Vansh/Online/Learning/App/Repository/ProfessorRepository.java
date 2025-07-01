package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Professors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<Professors,String> {
    List<Professors> findByStatus(String status);
}
