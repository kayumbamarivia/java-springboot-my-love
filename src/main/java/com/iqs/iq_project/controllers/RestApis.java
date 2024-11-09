package com.iqs.iq_project.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestApis {
    @GetMapping("/api/rest/")
    public String api(){
        return "Hello, Springboot People!";
    }
}
