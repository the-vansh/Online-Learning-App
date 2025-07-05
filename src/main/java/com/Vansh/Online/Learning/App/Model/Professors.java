package com.Vansh.Online.Learning.App.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Professors {
    @Id
    private String professorUsername; // this is basically the email of the professor
    private String professorName;
    private String degreeCompleted;
    private String institutionName;
    private String professorDepartment;
    private String experience;
    private String professorPhone;
    private String professorGender;
    private String professorPassword;
    private String professorImageUrl;
    private String status;


    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Courses> courses = new ArrayList<>();

    // THESE LINE CAN BE DELETED JUST HERE BECOUSE TILL NOW I DONT THE FRONTENT REQUIRMENT CAN CHANGE


}
