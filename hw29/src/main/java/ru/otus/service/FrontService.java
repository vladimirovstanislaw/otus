package ru.otus.service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontService {
    @GetMapping({"/users"})
    public String usersView(Model model) {
        return "users";
    }
}
