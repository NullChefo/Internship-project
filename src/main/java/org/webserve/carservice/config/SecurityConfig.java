package org.webserve.carservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationFilter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.webserve.carservice.security.JwtAuthenticationProvider;
import org.webserve.carservice.security.JwtHelper;
import org.webserve.carservice.security.WebserveUserDetailsService;

import javax.sql.DataSource;

import static org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType.H2;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //TODO comment out
//    @Bean
//    public DataSource dataSource(DataSource dataSource) {
//
//        return new EmbeddedDatabaseBuilder()
//                .setType(H2)
//                .addScript("classpath:sql/ser-init.sql")
//                .build();
//    }

    //TODO comment out
//    @Bean
//    public UserDetailsManager users(WebserveUserDetailsService webserveUserDetailsService) {
//        return (UserDetailsManager) webserveUserDetailsService;
//    }
//
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder,
//                                JwtHelper jwtHelper,
                                WebserveUserDetailsService webserveUserDetailsService) throws Exception {
        authenticationManagerBuilder
//                .authenticationProvider(new JwtAuthenticationProvider(jwtHelper))
                .userDetailsService(webserveUserDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return  authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    UserDetailsManager users(DataSource dataSource) {
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .accountExpired(false)
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//                .accountExpired(false)
//                .roles("USER", "ADMIN")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        users.createUser(admin);
//        return users;
//    }
//    @Bean
//    public WebserveUserDetailsService userDetailsService(WebserveUserDetailsService webserveUserDetailsService){
//        return webserveUserDetailsService;
//    }

//    @Bean
//    public static PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .formLogin();
        return httpSecurity.build();
    }
}
