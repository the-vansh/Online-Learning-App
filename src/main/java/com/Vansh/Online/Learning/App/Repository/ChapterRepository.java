package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Chapters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapters,Integer> {
    List<Chapters> findByCourse_CourseId(int courseid);
}
