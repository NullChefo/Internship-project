package org.webserve.carservice.home.web;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping
    public String home() {
        return "home/home";
    }

    @GetMapping("/login")
    public String login(){
        return "home/login";
    }

    @GetMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "home/logout";
    }

}
