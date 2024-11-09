package com.iqs.iq_project.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @Value("${app.username}")
    private String appUsername;
    
	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("username", "Kayumba Jean Marie Vianney");
        model.addAttribute("user", appUsername);
        return "index";
    }

}
