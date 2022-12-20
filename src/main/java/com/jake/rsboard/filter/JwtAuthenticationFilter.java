package com.jake.rsboard.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jake.rsboard.config.auth.PrincipalDetails;
import com.jake.rsboard.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 스프링 시큐리티에서  UsernamePasswordAuthenticationFilter 가 있음.
// /login 요청해서 username, password 전송하면(post)
// UsernamePasswordAuthenticationFilter 가 동작함
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired AuthenticationManagerBuilder authenticationManagerBuilder;

    // login 요청을 하면 로그인 시도를 위해서 실행되는 함수
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter: 로그인 시도중");

        // 1. username, password 받음
        try {
//            System.out.println(request.getInputStream().toString());

//            BufferedReader br = request.getReader();
//
//            String input = null;
//            while((input = br.readLine())!=null) {
//                System.out.println("input: " + input);
//            }
            ObjectMapper om = new ObjectMapper();
            User user = om.readValue(request.getInputStream(), User.class);
            System.out.println(user);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
            System.out.println(authenticationToken);

            // PrincipalDetailsService 의 loadByUsername() 함수가 실행됨
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            System.out.println(authentication);

            // authentication 객체가 session 영역에 저장됨 => 로그인이 되었다는 뜻.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println("로그인 완료됨: "+principalDetails.getUsername());
            
            // authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return 해주면 됨
            // 리턴의 이류는 권한 관리를 security 가 대신 해주기 때문에 편하려고 하는 것
            // 굳이 jwt 토큰을 사용하면서 세션을 만들 이유가 없음.. 단지 권한 처리 때문에 session에 넣어줌
            
            // JWT 토큰을 만들어줌
            
            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("=====================");
        // 2. 정상인지 로그인 시도를 해봄 authenticationManager 로 로그인 시도를 하면! PrincipalDetailsService 가 호출 loadUserByUserName 실행됨
        // 3. PrincipalDetails 를 세션에 담고(권한 관리를 해주기 위해서)
        // 4. Jwt Token 을 만들어서 응답해주면됨

//        return super.attemptAuthentication(request, response);
        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행 됨
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication 실행됨: 인증이 완료되었다.");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();



        super.successfulAuthentication(request, response, chain, authResult);
    }
}
