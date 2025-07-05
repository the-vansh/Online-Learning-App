package com.Vansh.Online.Learning.App.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String learnerGender;
    private String learnerImageUrl;
    @OneToMany(mappedBy = "learner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Enrollment> enrollments = new ArrayList<>();

    // THIS IS NOT NECESSARY THIS CAN BE ACCORDING THE NEED OF THE DATA AT THE FRONT END
}
