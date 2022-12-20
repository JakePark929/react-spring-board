package com.jake.rsboard.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString
@Entity
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter private String userId;
    @Setter private String password;
    @Setter private String email;
    @Setter private String role; //ROLE_USER, ROLE_ADMIN
    @Setter private String roles;

    @Setter private String provider;
    @Setter private String providerId;
//    @CreationTimestamp
//    private Timestamp loginDate;
    @CreationTimestamp
    private Timestamp createDate;

    public List<String> getRolesList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
    @Builder
    public User(
                String userId,
                String password,
                String email,
                String role,
                String provider,
                String providerId,
                Timestamp createDate) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.createDate = createDate;
    }
}
