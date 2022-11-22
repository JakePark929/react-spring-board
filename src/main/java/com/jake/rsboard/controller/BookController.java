package com.jake.rsboard.controller;

import com.jake.rsboard.domain.Book;
import com.jake.rsboard.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @PostMapping("/book")
    public ResponseEntity<?> save(@RequestBody Book book) {
//        List<Book> abc = new ArrayList<>(); // 제네릭 타입 생략가능
        return new ResponseEntity<>(bookService.insertBook(book), HttpStatus.CREATED); // 201
    }

    @GetMapping("/book")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookService.selectBooks(), HttpStatus.OK); // 200
    }

    @GetMapping("book/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.selectBook(id), HttpStatus.OK); // 200
    }

    @PutMapping("book/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK); // 200
    }

    @DeleteMapping("book/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.deleteBook(id), HttpStatus.OK); // 200
    }
}
