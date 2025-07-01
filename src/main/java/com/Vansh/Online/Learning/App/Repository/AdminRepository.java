package com.Vansh.Online.Learning.App.Repository;

import com.Vansh.Online.Learning.App.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {
}
