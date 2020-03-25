package com.login.login_security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/usuario")
    public String user(){
        return "user";
    }
}
