package com.Vansh.Online.Learning.App.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Chapters {
    @Id
    private int chapterId;
    private int chapterNumber;
    private String chapterName;
    private String chapterUrl;
    private String chapterCreatedDate;
    private String chapterPublicId;
    @ManyToOne
    @JoinColumn(name = "course_id", referencedColumnName = "courseId")
    @JsonBackReference
    private Courses course;
}
