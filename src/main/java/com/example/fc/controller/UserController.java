package com.example.fc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String user(){
        return "ex";
    }

    @GetMapping("/member")
    public String userForm(){
        return "/member/memberForm";
    }
}
