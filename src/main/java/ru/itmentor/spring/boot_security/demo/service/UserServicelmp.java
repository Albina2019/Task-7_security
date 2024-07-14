package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.dto.CreateUser;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServicelmp implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServicelmp(UserRepository userRepository, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }


    @Transactional
    @Override
    public void save(CreateUser createUser) {
        Set<Role> roles = createUser.getRoles().stream().map(role -> roleRepository.findByName
                ("ROLE_" + role.toUpperCase()).orElseThrow(() -> new RuntimeException("Роль не найдена")))
                .collect(Collectors.toSet());
        User user = new User();
        user.setEmail(createUser.getEmail());
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        user.setName(createUser.getName());
        user.setLastName(createUser.getLastName());
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void update(Long id, CreateUser createUser) {
        Set<Role> roles = createUser.getRoles().stream().map(role -> roleRepository.findByName
                        ("ROLE_" + role.toUpperCase()).orElseThrow(() -> new RuntimeException("Роль не найдена")))
                .collect(Collectors.toSet());
        User user = new User();
        user.setEmail(createUser.getEmail());
        user.setPassword(passwordEncoder.encode(createUser.getPassword()));
        user.setName(createUser.getName());
        user.setLastName(createUser.getLastName());
        user.setRoles(roles);
        user.setId(id);
        userRepository.save(user);
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
