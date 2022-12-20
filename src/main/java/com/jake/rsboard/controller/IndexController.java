package com.jake.rsboard.controller;

import com.jake.rsboard.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller // View를 리턴하겠다!
public class IndexController {
    private final EmailSenderService emailSenderService;

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }

    @GetMapping("/mail")
    @ResponseBody
    public String mail() {
        emailSenderService.sendEmail("jepark@wise.co.kr", "이것은 제목", "이것은 내용");
        return "mail";
    }

    // /login 을 스프링시큐리티가 낚아챔 - Security Config 파일 생성 후 작동안함
    @GetMapping("/login-form")
    public String loginForm() {
        return "/index.html";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/index.html";
    }
    
    @GetMapping("/join")
    public String join() {
        return "/index.html";
    }

}
