package com.example.shetuancommunity.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @GetMapping("/hello")
    public  String index()
    {
         return  "index";
    }
}
