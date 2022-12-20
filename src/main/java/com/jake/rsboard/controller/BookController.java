package com.jake.rsboard.controller;

import com.jake.rsboard.domain.Book;
import com.jake.rsboard.service.BookService;
import com.jake.rsboard.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    // security (라이브러리 적용) - CORS 정책을 가지고 있음.(시큐리티가 CORS를 해제해야함)
    // BookController 진입 직전
    @CrossOrigin
    @PostMapping("/book")
    public ResponseEntity<?> save(@RequestBody Book book) {
//        List<Book> abc = new ArrayList<>(); // 제네릭 타입 생략가능
//        throw new IllegalArgumentException("잘못날림");
        return new ResponseEntity<>(bookService.insertBook(book), HttpStatus.CREATED); // 201
    }

    @CrossOrigin
    @GetMapping("/book")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookService.selectBooks(), HttpStatus.OK); // 200
    }

    @CrossOrigin
    @GetMapping("book/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.selectBook(id), HttpStatus.OK); // 200
    }

    @CrossOrigin
    @PutMapping("book/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK); // 200
    }

    @CrossOrigin
    @DeleteMapping("book/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK); // 200
    }
}
