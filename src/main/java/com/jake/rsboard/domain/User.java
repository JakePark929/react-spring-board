package com.jake.rsboard.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@ToString
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter private String userId;
    @Setter private String password;
    @Setter private String email;
    @Setter private String role; //ROLE_USER, ROLE_ADMIN

//    @CreationTimestamp
//    private Timestamp loginDate;
    @CreationTimestamp
    private Timestamp createDate;
}
