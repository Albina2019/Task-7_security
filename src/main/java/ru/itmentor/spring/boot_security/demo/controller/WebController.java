package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;
@Controller
@RequestMapping
public class WebController {
    private final UserService userService;

    @Autowired
    public WebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String menuPage(Model model) {
       return "menuPage";
    }

    @GetMapping("/allUsers")
    public String getAllUsers() {
        return "user";
    }
    @GetMapping("/login")
    public String login() {
      return "loginPage";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @GetMapping("/search")
    public String getUserById(@RequestParam(required = false) Long id, Model model) {
        User user = userService.findById(id);
        if(user == null) {
            return  "redirect:/";
        }
        model.addAttribute("user", user);
        return "search";
    }

    @GetMapping("/new")
    public String showCreateUserFrom() {
        return "addUser";
    }


}