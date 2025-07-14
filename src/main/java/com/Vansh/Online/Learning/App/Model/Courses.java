package com.Vansh.Online.Learning.App.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String coursePublicId;
    private int enrollmentCount;

    @ManyToOne
    @JoinColumn(name = "Professor_username", referencedColumnName = "professorUsername")
    @JsonIgnoreProperties("courses")
    private Professors professor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Chapters> chapters = new ArrayList<>();
     // THIS IS NOT NECESSARY THIS CAN BE ACCORDING THE NEED OF THE DATA AT THE FRONT END

}
