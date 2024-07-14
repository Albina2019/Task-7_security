package ru.itmentor.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.itmentor.spring.boot_security.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomUserDetailsService customUserDetailsService; // сервис, с помощью которого тащим пользователя

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Нам нужно добавить CharacterEncodingFilter перед фильтрами, которые читают свойства запроса в первый раз.
        // Есть securityFilterChain (стоит вторым. после metrica filter) , и мы можем добавить наш фильтр внутри него.
        // Первый фильтр (внутри цепочки безопасности), который читает свойства,
        // - это CsrfFilter, поэтому мы помещаем перед ним CharacterEncodingFilter.

        http
                .formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                .loginProcessingUrl("/login") // указываем action с формы логина
                .permitAll();// даем доступ к форме логина всем

        http.logout()
                .permitAll()// разрешаем делать логаут всем
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// указываем URL логаута
                .logoutSuccessUrl("/login?logout") // указываем URL при удачном логауте
                //выключаем кросс-доменную секьюрность
                .and().csrf().disable();

        http
                .authorizeRequests()// делаем страницу регистрации недоступной для авторизированных пользователей
                .antMatchers("/").permitAll() // доступность всем
                //страница аутентификации доступна всем
                .antMatchers("/", "/login", "menuPage").permitAll()
                .antMatchers("/api/user/profile", "/profile").hasAnyRole("USER", "ADMIN")
                .antMatchers("/editUser", "/addUser", "/allUsers", "/api/admin/**", "/new", "/save").hasRole("ADMIN")
                .anyRequest().hasRole("ADMIN")
                .and().formLogin().loginPage("/login").permitAll();


    }

    // Необходимо для шифрования паролей. В данном примере не используется, отключен
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}

    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

