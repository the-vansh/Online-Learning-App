package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Chapters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapters,Integer> {
}
