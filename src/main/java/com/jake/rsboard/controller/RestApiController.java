package com.jake.rsboard.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @CrossOrigin
    @GetMapping("/hello")
    public String hello() {
        return "<h1>Hello</h1>";
    }

    @CrossOrigin
    @PostMapping("/token")
    public String token() {
        return "<h1>token</h1>";
    }
}
