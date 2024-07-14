package ru.itmentor.spring.boot_security.demo.dto;

import lombok.Data;
import ru.itmentor.spring.boot_security.demo.model.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Data
public class CreateUser {
    private String name;
    private String lastName;
    private String email;
    private String password;

    private Set<String> roles = new HashSet<>();
}
