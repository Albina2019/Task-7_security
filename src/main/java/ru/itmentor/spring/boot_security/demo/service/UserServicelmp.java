package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;
import ru.itmentor.spring.boot_security.demo.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
//public class UserServicelmp implements UserService {
//    private final UserRepository userRepository;
//    private final RoleRepository roleRepository;
//    private final PasswordEncoder passwordEncoder;
//    @Autowired
//    public UserServicelmp(UserRepository userRepository, RoleRepository roleRepository,
//                          PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public Optional<User> findByEmail(String email) {
//        return Optional.ofNullable(userRepository.findByEmail(email));
//    }
//
//
//    @Transactional
//    @Override
//    public void save(User user) {
//        userRepository.save(user);
//    }
//
//    @Override
//    public User findById(Long id) {
//        return userRepository.getOne(id);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userRepository.findAll();
//    }
//
//    @Transactional
//    @Override
//    public void update(Long id, User user) {
//        userRepository.saveAndFlush(user);
//    }
//
//
//    @Transactional
//    @Override
//    public void deleteById(Long id){
//        userRepository.deleteById(id);
//    }
//
//    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return (UserDetails) userRepository.findByEmail(email);
//    }
//}
@Service
public class UserServicelmp implements UserDetailsService {

    private final UserRepository userRepository;

    public UserServicelmp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}