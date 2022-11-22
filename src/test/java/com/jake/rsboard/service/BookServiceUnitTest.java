package com.jake.rsboard.service;

import com.jake.rsboard.domain.Book;
import com.jake.rsboard.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * 단위 테스트 (Service와 관련된 애들만 메모리에 띄우면 됨)
 * BoardRepository => 가짜 객체로 만들 수 있음
 */
@ExtendWith(MockitoExtension.class)
class BookServiceUnitTest {
    @InjectMocks // BookService 객체가 만들어 질 때 BookServiceUnitTest 파일에 @Mock로 등록된 모든 애들을 주입받는다
    private BookService bookService;
    @Mock
    private BookRepository bookRepository;

    @DisplayName("저장하기 테스트")
    @Test
    void insertBook() {
//        // BDD Mockito 방식
//        // Given
//        Book book = new Book();
//        book.setTitle("책제목1");
//        book.setAuthor("책저자1");
//
//        // stub - 동작 지정
//        when(bookRepository.save(book)).thenReturn(book);
//
//        // test execute
//        Book bookEntity = bookService.insertBook(book);
//
//        // Then
//        assertEquals(bookEntity.);
    }

    @Test
    void selectBook() {
    }

    @Test
    void selectBooks() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}