package com.loanservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loans")
public class HomeController {

    public String home() {
        return "Welcome to the loans service";
    }
}
