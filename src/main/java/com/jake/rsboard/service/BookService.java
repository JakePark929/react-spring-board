package com.jake.rsboard.service;

import com.jake.rsboard.domain.Book;
import com.jake.rsboard.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 서비스가 되면 기능을 정의할 수 있고, 트랜잭션을 관리할 수 있음
@RequiredArgsConstructor // final 붙어있는 변수의 constructor 자동생성
@Service
public class BookService {

    // 함수 => 송금() -> 레파지토리에 여러개의 함수 실행 -> commit or rollback
    private final BookRepository bookRepository;

    // 저장하기
    @Transactional // 서비스 함수가 종료될 때 commit할 지 rollback할지 트랜잭션 관리하겠다
    public Book insertBook(Book book) {
        return bookRepository.save(book); // save하고 save한 entity를 그대로 리턴
    }

    // 한건 가져오기
    @Transactional(readOnly = true) // JPA의 변경감지 내부기능.. 활성화 안됨..
    // 내부 연산 감소, update 정합성 유지, insert의 유령데이터 현상(팬텀현상) 못막음
    public Book selectBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해 주세요."));
    }

    // 모두 가져오기
    @Transactional(readOnly = true)
    public List<Book> selectBooks() {
        return bookRepository.findAll();
    }

    // 수정하기
    @Transactional
    public Book updateBook(Long id, Book book) {
        // 더티체킹 update
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해 주세요.")); // 영속화 (book 오브젝트) -> 영속성 컨텍스트에 보관
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    } // 함수 종료 => 트랜잭션 종료 => 영속화 되어있는 데이터를 DB로 갱신(flush) => commit ======> 더티체킹

    // 삭제하기
    @Transactional
    public String deleteBook(Long id) {
        bookRepository.deleteById(id); // 오류가 터지면 익셉션을 탐.. 신경 안써도 됨
        return "ok";
    }
}
