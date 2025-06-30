package com.Vansh.Online.Learning.App.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String status;
}
