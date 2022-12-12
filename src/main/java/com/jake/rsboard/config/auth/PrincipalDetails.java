package com.jake.rsboard.config.auth;

import com.jake.rsboard.domain.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킴.
// 로그인 진행이 완료되면 session 을 만들어 줌(Security ContextHolder: security 가 가지고 있는 세션, key 값으로 구분)
// 오브젝트는 정해져 있음 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 됨
// User 오브젝트타입 => UserDetails 타입 객체

// Security Session => Authentication => UserDetails(PrincipalDetails 에 구현)

@Data
public class PrincipalDetails implements UserDetails, OAuth2User {

    private User user; // 콤포지션
    private Map<String, Object> attributes;

    // 일반 로그인
    public PrincipalDetails(User user) {
        this.user = user;
    }

    // OAuth 로그인
    public PrincipalDetails(User user, Map<String, Object> attributes) {
        this.user = user;
        this.attributes = attributes;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    // 해당 User 의 권한을 리턴하는 곳!
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add((GrantedAuthority) () -> user.getRole());
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserId();
    }

    @Override
    public boolean isAccountNonExpired() { // 계정 만료됬니?
        return true; // 아니오
    }

    @Override
    public boolean isAccountNonLocked() { // 계정 잠겼니?
        return true; // 아니오
    }

    @Override
    public boolean isCredentialsNonExpired() { // 계정 유효기간 지났니?(비밀번호 등)
        return true; // 아니오
    }

    @Override
    public boolean isEnabled() {
        // 우리사이트 1년 동안 회원이 로그인을 안하면 휴면 계정으로 하기로 함.
        // user.getLoginDate(); // 현재시간 - 로긴시간 => 1년 초과 시 return false..

        return true; // 아니오
    }

    @Override
    public String getName() {
//        return attributes.get("sub");
        return null;
    }
}
