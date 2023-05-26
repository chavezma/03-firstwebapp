package com.springboot.tutorial.firstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetailsMat = createNewUser("matias", "matias");
        UserDetails userDetailsAra = createNewUser("araceli", "araceli");

        return new InMemoryUserDetailsManager(userDetailsMat, userDetailsAra);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder
            = input -> passwordEncoder().encode(input);

        return User.builder()
            .passwordEncoder(passwordEncoder)
            .username(username)
            .password(password)
            .roles("USER", "ADMIN")
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //All URLs are protected
    //A login form is shown for unauthorized requests
    //CSRF disable
    //Frames

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated()
        );
        http.formLogin(withDefaults());

        http.csrf().disable();
        http.headers(headers -> headers.frameOptions().disable());

        return http.build();
    }
}
