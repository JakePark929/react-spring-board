package com.jake.rsboard.repository;

import com.jake.rsboard.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 를 적어야 스프링 IoC에 빈으로 등록이 되는데
// JpaRepository 를 extends하면 생략가능함
// JpaRepository 는 CRUD함수를 들고 있음
public interface BookRepository extends JpaRepository<Book, Long> {
}
