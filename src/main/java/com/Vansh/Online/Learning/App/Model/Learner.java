package com.Vansh.Online.Learning.App.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Learner {
    @Id
    private int learnerId;
    private String learnerName;
    private String learnerEmail;
    private String learnerPassword;
    private String learnerPhoneNumber;
}
