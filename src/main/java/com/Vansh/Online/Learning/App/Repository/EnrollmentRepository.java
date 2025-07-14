package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findByCourse_CourseId(int courseid);
    List<Enrollment> findByLearner_LearnerUsername(String learnerusername);
    // Count enrollments after a certain date
    long countByEnrollmentDateAfter(LocalDate date);

    // Count enrollments on a specific date
    long countByEnrollmentDate(LocalDate date);

    @Query("SELECT COUNT(e) FROM Enrollment e WHERE e.course.courseId = :courseId")
    int countEnrollmentsByCourseId(@Param("courseId") int courseId);

}
