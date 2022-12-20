package com.jake.rsboard.controller;

import com.jake.rsboard.config.auth.PrincipalDetails;
import com.jake.rsboard.domain.User;
import com.jake.rsboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

    @CrossOrigin
    @GetMapping("/test/login")
    @ResponseBody
    public String testLogin(
            Authentication authentication, // DownCasting 으로 getUser() 하는 방법
            @AuthenticationPrincipal PrincipalDetails userDetails // Annotation 으로 getUser() 하는 방법
            ) { // DI(Dependency Injection: 의존성 주입)
        System.out.println("/test/login ============");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication: " + principalDetails.getUser());
        System.out.println("userDetails: " + userDetails.getUser());
        return "세션 정보 확인하기";
    }

    @CrossOrigin
    @GetMapping("/test/oauth/login")
    @ResponseBody
    public String testOAuthLogin(
            Authentication authentication,
            @AuthenticationPrincipal OAuth2User oauth
    ) { // DI(Dependency Injection: 의존성 주입)
        System.out.println("/test/oauth/login ============");
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication: " + oauth2User.getAttributes());
        System.out.println("oauth2User: " + oauth.getAttributes());
        return "OAuth 세션 정보 확인하기";
    }

    // OAuth 로그인을 해도 PrincipalDetails
    // 일반 로그인을 해도 PrincipalDetails
    @GetMapping("/user")
    @ResponseBody
    public String user( // 어노테이션 활성화
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        System.out.println("principalDetails: " + principalDetails.getUser());
        return "user";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "개인정보";
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 메소드 실행되기 직전에 실행
//    @PostAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // 메소드 실행된 후에 실행
    @GetMapping("/data")
    @ResponseBody
    public String data() {
        return "데이터정보";
    }
}
