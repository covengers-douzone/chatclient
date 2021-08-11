package com.douzone.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "main/index";
    }

    @GetMapping("/main/success")
    public String success(){
        return "main/success";
    }

}