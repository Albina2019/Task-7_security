package ru.itmentor.spring.boot_security.demo.controller;

import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.dto.CreateUser;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserSubmit(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "userDeleted";
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody CreateUser createUser) {
        userService.save(createUser);
        return "Пользователь создан";
    }
    @GetMapping("/read/{id}")
    public User readUser(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @PutMapping("/upDate/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody CreateUser createUser) {
        userService.update(id, createUser);
        return "userUpDate";
    }

}