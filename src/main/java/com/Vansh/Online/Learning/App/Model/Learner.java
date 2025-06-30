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
    private String learnerUsername; // this is basically the email of the learner
    private String learnerName;
    private String learnerPassword;
    private String learnerPhoneNumber;
}
