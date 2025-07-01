package com.Vansh.Online.Learning.App.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Chapters> chapters = new ArrayList<>();
}
