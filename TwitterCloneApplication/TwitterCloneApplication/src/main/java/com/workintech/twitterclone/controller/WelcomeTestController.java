package com.workintech.twitterclone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeTestController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome";
    }
}
