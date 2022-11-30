package com.jake.rsboard.repository;

import com.jake.rsboard.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository가 들고 있음
// @Repository라는 어노테이션이 없어도 IoC 가능, JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Long> {
    // findBy 는 규칙 => Username은 문법
    // select * from user where username = username(파라메터)?
    User findByUserId(String username);
//    User findByEmail(String email); // select * from user where email = ? // JPA naming query? Jpa query method!
}
