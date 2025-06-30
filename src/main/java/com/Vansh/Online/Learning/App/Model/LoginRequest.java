package com.Vansh.Online.Learning.App.Model;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
    String role;
}
