package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.itmentor.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    void save(User user);

User findById(Long id);

List<User> findAll();

void update(Long id, User user);

void deleteById(Long id);

    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
