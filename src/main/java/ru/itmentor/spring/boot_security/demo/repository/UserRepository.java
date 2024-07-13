package ru.itmentor.spring.boot_security.demo.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    List<User> findAll();


    User findByEmail(String email);
}

