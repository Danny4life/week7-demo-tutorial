package com.osiki.RegLogPageDemo.controller;

import com.osiki.RegLogPageDemo.model.UserModel;
import com.osiki.RegLogPageDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UserModel());

        return "register_page";

    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel){
        System.out.println("register request " + userModel);

        UserModel registerUser = userService.registerUser(userModel.getLogin(), userModel.getEmail(),
                userModel.getPassword());

        return registerUser == null ? "error_page" : "redirect:/login";

    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model){
        System.out.println("login request " + userModel);

        UserModel auth = userService.authenticate(userModel.getLogin(), userModel.getPassword());

        if(auth != null){
            model.addAttribute("userLogin", auth.getLogin());

            return "personal_page";
        }else {
            return "error_page";
        }

    }
}
