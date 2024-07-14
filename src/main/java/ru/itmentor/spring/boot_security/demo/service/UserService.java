package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itmentor.spring.boot_security.demo.dto.CreateUser;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    void save(CreateUser createUser);

    User findById(Long id);

    List<User> findAll();

    void update(Long id, CreateUser createUser);

    void deleteById(Long id);


    User findByEmail(String name);
}   
