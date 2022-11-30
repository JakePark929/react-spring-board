package com.jake.rsboard.config.auth;

import com.jake.rsboard.domain.User;
import com.jake.rsboard.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login") 으로 걸었기 때문에
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어 있는 loadByUsername 함수가 실행됨
@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public PrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Security ContextHolder: Security Session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username: {}", username);
        User userEntity = userRepository.findByUserId(username);
        log.info("userAccountEntity: {}", userEntity);
        System.out.println(userEntity);
        if(userEntity != null) {
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
