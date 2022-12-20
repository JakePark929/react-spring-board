package com.jake.rsboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jake.rsboard.domain.Book;
import com.jake.rsboard.repository.BookRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 통합 테스트(모든 Bean들을 똑같이 Ioc에 올리고 테스트 하는 것)
 * WebEnvironment.MOCK = 실제 톰캣을 올리는 것이 아니라, 다른 톰캣으로 테스트
 * WebEnvironment.RANDOM_PORT = 실제 톰캣으로 테스트
 * @AutoConfigureMockMvc MockMvc를 IoC에 등록해줌.
 * @Transactional 각각의 테스트 함수가 종료될 때 마다 트랜잭션을 rollback 해주는 함수
 */
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 모든 애들이 메모리에 다뜸
public class BookControllerIntegreTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void init() {
//        List<Book> books = new ArrayList<>();
//        books.add(new Book(1L, "스프링부트 따라하기", "jake"));
//        books.add(new Book(2L, "리액트 따라하기", "jake"));
//        books.add(new Book(3L, "JUnit 따라하기", "jake"));
//        bookRepository.saveAll(books);

//        entityManager.createNativeQuery("ALTER TABLE book ALTER COLUMN id RESTART WITH 1").executeUpdate(); // h2 test 용
        entityManager.createNativeQuery("ALTER TABLE book AUTO_INCREMENT 1").executeUpdate(); // mysql test용
    }

//    @AfterEach
//    public void end() {
//        boardRepository.deleteAll();
//    }

    @DisplayName("save test")
    @Test
    void save() throws Exception {
        // Given(테스트를 하기 위한 준비)
        Book book = new Book(null, "스프링 따라하기", "jake");
        String content = new ObjectMapper().writeValueAsString(book);
        // 테스트 스텁이 필요없어짐
        // when(bookService.insertBook(book)).thenReturn(new Book(1L, "스프링 따라하기", "jake"));

        // When (테스트 실행)
        ResultActions resultActions = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // then(검증)
        resultActions
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("스프링 따라하기"))
                .andDo(MockMvcResultHandlers.print());

    }

    @DisplayName("select test")
    @Test
    void findAll() throws Exception {
        // Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링부트 따라하기", "jake"));
        books.add(new Book(2L, "리액트 따라하기", "jake"));
        books.add(new Book(3L, "JUnit 따라하기", "jake"));
        bookRepository.saveAll(books);

        // When
        ResultActions resultAction = mockMvc.perform(get("/book")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[2].title").value("JUnit 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("select one test")
    @Test
    void findById() throws Exception {
        // Given
        Long id = 2L;

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링부트 따라하기", "jake"));
        books.add(new Book(2L, "리액트 따라하기", "jake"));
        books.add(new Book(3L, "JUnit 따라하기", "jake"));
        bookRepository.saveAll(books);

        // When
        ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("리액트 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("update test")
    @Test
    void update() throws Exception {
        // Given
        Long id = 3L;

        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "스프링부트 따라하기", "jake"));
        books.add(new Book(2L, "리액트 따라하기", "jake"));
        books.add(new Book(3L, "JUnit 따라하기", "jake"));
        bookRepository.saveAll(books);

        Book book = new Book(null, "C++ 따라하기", "jake");
        String content = new ObjectMapper().writeValueAsString(book);

        // When
        ResultActions resultAction = mockMvc.perform(put("/book/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3L))
                .andExpect(jsonPath("$.title").value("C++ 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }
}
