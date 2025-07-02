package com.Vansh.Online.Learning.App.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    private int enrollmentId;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    private Courses course;

    @ManyToOne
    @JoinColumn(name = "learner_username", referencedColumnName = "learnerUsername")
    @JsonIgnoreProperties("enrollments")
    private Learner learner;

    private String enrollmentDate;
    private String status;
}
