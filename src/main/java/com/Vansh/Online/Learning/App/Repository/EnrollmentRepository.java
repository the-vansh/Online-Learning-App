package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
    List<Enrollment> findByCourse_CourseId(int courseid);
}
