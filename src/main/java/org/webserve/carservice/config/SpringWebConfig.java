package org.webserve.carservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SpringWebConfig {
    @Bean
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
