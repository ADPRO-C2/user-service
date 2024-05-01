package com.example.secondtreasurebe.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping("/")
    @ResponseBody
    public String createUserPage(Model model) {
        return "<h1>Hello World! This is SecondTreasure User Microservice.</h1>";
    }
}