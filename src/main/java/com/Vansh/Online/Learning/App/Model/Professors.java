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
    private int professorid;
    private String professoremail;
    private String professorname;
    private String degreecompleted;
    private String institutionname;
    private String professordepartment;
    private String experience;
    private String professorphone;
    private String professorgender;
    private String professorpassword;
    private String status;
}
