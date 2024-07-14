package ru.itmentor.spring.boot_security.demo.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @NonNull
    List<User> findAll();


    Optional<User> findByEmail(String email);
}

