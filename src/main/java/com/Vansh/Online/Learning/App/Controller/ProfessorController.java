package com.Vansh.Online.Learning.App.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the Professor Dashboard!";

    }
}
