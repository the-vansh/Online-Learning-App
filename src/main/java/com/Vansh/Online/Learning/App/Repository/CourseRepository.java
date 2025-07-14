package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Integer> {
    @Query("SELECT c FROM Courses c WHERE " +
            "LOWER(c.courseName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseDescription) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseType) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.courseLanguages) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Courses> findByKeyword(String keyword);
    List<Courses> findByProfessor_ProfessorUsername(String professorUsername);
    @Query("SELECT new map(c.courseName as courseName, c.enrollmentCount as enrollmentCount) " +
            "FROM Courses c ORDER BY c.enrollmentCount DESC LIMIT 5")
    List<Map<String, Object>> findTop5CoursesByEnrollmentCount();

}
