package com.jake.rsboard.config;

import com.jake.rsboard.config.oauth.PrincipalOauth2UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // 메모리에 띄워줌
@EnableWebSecurity // 활성화 - 스프링 시큐리티 필터가 스프링 필터체인에 등록됨
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화 - @Secured 로 메소드 부분제한
// , preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig { // >> 스프링 시큐리티 필터
    private final PrincipalOauth2UserService principalOauth2UserService;

    public SecurityConfig(PrincipalOauth2UserService principalOauth2UserService) {
        this.principalOauth2UserService = principalOauth2UserService;
    }

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
                .oauth2Login(oauth2 -> oauth2 // 구글 로그인이 완료된 뒤의 후처리가 필요함
                        .loginPage("/login-form")
                        .userInfoEndpoint()
                        .userService(principalOauth2UserService)
                )
                // 구글 로그인이 완료된 뒤의 후처리!! -> Tip. 코드받기 X, 액세스 토큰+사용자 프로필정보 O
                // 1. 코드받기(인증)
                // 2. 액세스 토큰(권한)
                // 3. 사용자 프로필 정보를 가져옴
                // 4-1 그 정보를 토대로 회원가입을 자동으로 진행시키기도 함
                // 4-2 구글 -> (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> (집주소), 백화점 몰 ->(고객등급..) 추가 회원가입 진행
                .build();
    }

    @Bean // Bean: 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해줌
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }
}
