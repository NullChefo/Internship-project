package org.webserve.carservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.webserve.carservice.security.WebserveUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
                                WebserveUserDetailsService webserveUserDetailsService) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(webserveUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // username: admin
        // password: password

        // username: user
        // password: password

        httpSecurity.authorizeHttpRequests().mvcMatchers("/login").permitAll()
                .mvcMatchers("cars/viewAllCars").hasRole("ADMIN")
                .mvcMatchers("/actuator/**").hasRole("ADMIN")
                .mvcMatchers("/**").authenticated()
                .anyRequest().denyAll()
                .and()
                .formLogin().loginPage("/login");
        return httpSecurity.build();
    }
}
