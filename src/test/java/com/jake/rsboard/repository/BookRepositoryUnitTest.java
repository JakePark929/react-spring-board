package com.jake.rsboard.repository;

import com.jake.rsboard.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

// 단위 테스트 (DB 관련된 Bean이 IoC에 등록되면 됨)
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // ANY 가짜 DB로 테스트, Replace.NONE 실제 DB로 테스트
@DataJpaTest // Repository 들을 다 IoC에 등록 해줌
class BookRepositoryUnitTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save_test() {
        // Given
        Book book = new Book(null,"책제목1","책저자1");

        // When
        Book bookEntity = bookRepository.save(book);

        // Then
        assertEquals("책제목1",bookEntity.getTitle());
    }
}