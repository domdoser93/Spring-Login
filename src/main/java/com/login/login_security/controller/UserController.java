package com.login.login_security.controller;

import com.login.login_security.model.User;
import com.login.login_security.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/?error=true")
    public String indexFail(){
        return "index";
    }
    
    @GetMapping("/usuario")
    public String usuario(Model model){
        model.addAttribute("usuario", new User());        
        return "user";
    }
    
    @GetMapping("/userForm")
    public String userForm(){
        return "userForm";
    }
    
    @PostMapping("/usuario")
    public String createUser(@Valid @ModelAttribute("usuario")User user, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", user);
            model.addAttribute("formTab","active");
        } else {
            try {
                userService.createUser(user);
                model.addAttribute("formSuccesMessage","Usuario Registrado con éxito");
                return "index";
            } catch (Exception ex) {
                model.addAttribute("formErrorMessage",ex.getMessage());
                model.addAttribute("usuario", user);
                model.addAttribute("formTab","active");
            }
        }
        return "user";
    }
}
