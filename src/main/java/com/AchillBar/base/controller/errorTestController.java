package com.AchillBar.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class errorTestController {

    @GetMapping("/testError/{id}")
    public String Error(@PathVariable("id") Integer id) {
        int a = 10 / 0;
        return "";

    }
}
