package com.jake.rsboard.controller;

import com.jake.rsboard.domain.User;
import com.jake.rsboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @CrossOrigin
    @PostMapping("/join-user")
    public ResponseEntity<?> save(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<>(userService.insertUser(user), HttpStatus.CREATED); // 201
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 메소드 실행되기 직전에 실행
//    @PostAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 메소드 실행되기 직전에 실행
    @GetMapping("/data")
    @ResponseBody
    public String data() {
        return "데이터정보";
    }
}
