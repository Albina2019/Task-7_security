package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping
public class Controller {
    private final UserService userService;

    @Autowired
    public Controller(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") UserService userService) {
        this.userService = userService;
    }


    @GetMapping({"/",""})
    public String index(){
        return "Welcome";
    }

    @GetMapping("/")
    public String getAllUsers(Model model) {
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "index";
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
    public String showCreateUserFrom(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "addUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/";
        } else model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping("/edit/{id}")
    public String updateUserSubmit(@PathVariable Long id, @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserSubmit(@PathVariable ("id") Long id) {
        userService.deleteById(id);
        return "redirect:/";
    }

}