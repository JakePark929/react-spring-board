package com.jake.rsboard.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity // 서버 실행시에 Object Relation Mapping 됨. (H2에 Table 생성됨)
public class Book {
    @Id // PK를 해당 변수로 함.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 해당 데이터베이스 번호증가 전략을 따라감.
    private Long id;

    private String title;
    private String author;

    // 함수
//    public static setBook(Dto dto) {
//        title = dto.getTitle();
//        author = dto.getAuthor();
//    }
}
