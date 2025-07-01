package com.Vansh.Online.Learning.App.Service;

import com.Vansh.Online.Learning.App.Model.Admin;
import com.Vansh.Online.Learning.App.Model.LoginRequest;
import com.Vansh.Online.Learning.App.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminLoginService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public ResponseEntity<?> verifyAdmin(LoginRequest loginrequest){
        Admin admin = adminRepository.findById(loginrequest.getUsername()).orElse(null);
        if (admin == null || !passwordEncoder.matches(loginrequest.getPassword(), admin.getAdminPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        String token = jwtService.generateToken(admin.getAdminUsername(), "ADMIN");
        return ResponseEntity.ok().body(Map.of("token", token, "role", "ADMIN"));
    }
}
