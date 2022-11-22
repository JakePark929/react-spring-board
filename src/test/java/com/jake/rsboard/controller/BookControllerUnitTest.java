package com.jake.rsboard.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jake.rsboard.domain.Book;
import com.jake.rsboard.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 단위 테스트(Controller 관련 로직만 띄우기) Filter, ControllerAdvice, 최소한의 Bean으로 처리
//@ExtendWith(SpringExtension.class)
// Spring에서 Junit을 쓸때에는 반드시 확장 해줘야 함, WebMvcTest에 포함되어 생략
@WebMvcTest
class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // IoC 환경에 bean 등록 됨, 가짜 서비스
    private BookService bookService;

    // BDD Mockito 패턴
    @DisplayName("save test")
    @Test
    void save() throws Exception {
        // Given(테스트를 하기 위한 준비)
        Book book = new Book(null, "스프링 따라하기", "jake");
        String content = new ObjectMapper().writeValueAsString(book);
        // 테스트 스텁
        when(bookService.insertBook(book)).thenReturn(new Book(1L, "스프링 따라하기", "jake"));

        // When (테스트 실행)
        ResultActions resultAction = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // then(검증)
        resultAction
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
        when(bookService.selectBooks()).thenReturn(books);

        // When
        ResultActions resultAction = mockMvc.perform(get("/book")
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("스프링부트 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("select one test")
    @Test
    void findById() throws Exception {
        // Given
        Long id = 1L;
        when(bookService.selectBook(id)).thenReturn(new Book(1L, "자바 공부하기", "jake"));

        // When
        ResultActions resultAction = mockMvc.perform(get("/book/{id}", id)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("자바 공부하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("update test")
    @Test
    void update() throws Exception {
        // Given
        Long id = 1L;
        Book book = new Book(null, "C++ 따라하기", "jake");
        String content = new ObjectMapper().writeValueAsString(book);
        when(bookService.updateBook(id, book)).thenReturn(new Book(1L, "C++ 따라하기", "jake"));

        // When
        ResultActions resultAction = mockMvc.perform(put("/book/{id}",id)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("C++ 따라하기"))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("delete test")
    @Test
    void deleteById() throws Exception {
        // Given
        Long id = 1L;
        when(bookService.deleteBook(id)).thenReturn("ok");

        // When
        ResultActions resultAction = mockMvc.perform(delete("/book/{id}",id)
//                .accept(MediaType.TEXT_PLAIN)
        );

        // Then
        resultAction
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        MvcResult requestResult = resultAction.andReturn();
        String result = requestResult.getResponse().getContentAsString();

        assertEquals("ok", result);
    }
}