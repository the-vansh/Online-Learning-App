package com.Vansh.Online.Learning.App.Model;

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
public class Courses {
    @Id
    private int courseId;
    private String courseName;
    private String courseDescription;
    private String courseDuration;
    private String coursePrice;
    private String courseImageUrl;
    private String courseCreatedDate;
    private String courseType;
    private String courseLanguages;
    private int EnrollmentCount;


    @ManyToOne
    @JoinColumn(name = "Professor_username", referencedColumnName = "professorUsername")
    private Professors professor;
}
