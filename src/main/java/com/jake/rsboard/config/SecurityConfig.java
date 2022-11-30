package com.jake.rsboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // 메모리에 띄워줌
@EnableWebSecurity // 활성화 - 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화 - @Secured 로 메소드 부분제한
// , preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig { // >> 스프링 시큐리티 필터
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .mvcMatchers(
                                "/user/**"
                        ).authenticated() // 인증만되면 들어갈 수 있는 주소
                        .mvcMatchers(
                                "/manager/**"
                        ).hasAnyRole("MANAGER", "ADMIN")
                        .mvcMatchers(
                                "/admin/**"
                        ).hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .formLogin()
                    .loginPage("/login-form")
                    .usernameParameter("userId") // username 을 front 에서 username 으로 설정 하지 않았다면 parameter 를 설정 해줘야 함
                    .loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해줌 -> controller 에 login 이 필요없어짐
                    .defaultSuccessUrl("/") // 로그인시 default 는 "/" 특정페이지에서는 특정페이지로 이동
                    .and()
                .oauth2Login()
                    .loginPage("/login-form") // 구글 로그인이 완료된 뒤의 후처리가 필요함
                    .and()
                .build();
    }

    @Bean // Bean: 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
}
