package com.jake.rsboard.service;

import com.jake.rsboard.domain.User;
import com.jake.rsboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional // 서비스 함수가 종료될 때 commit할 지 rollback할지 트랜잭션 관리하겠다
    public User insertUser(User user) {
        user.setRole("ROLE_USER"); // 회원가입은 잘되지만 패스워드 암호화가 안됨!
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        return userRepository.save(user); // save하고 save한 entity를 그대로 리턴
    }
}
