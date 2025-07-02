package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Integer> {
    @Query("SELECT c FROM Courses c WHERE " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseLanguages) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Courses> findByKeyword(String keyword);
    List<Courses> findByProfessor_ProfessorUsername(String professorUsername);
}
