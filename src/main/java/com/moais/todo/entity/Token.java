package com.moais.todo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "todo.token")
public class Token { //토큰

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //인덱스

    @Column(name = "refresh_token")
    private String refreshToken; //Refresh 토큰

    @Column(name = "access_token")
    private String accessToken; //Access 토큰


    public Token() {

    }

    public Token(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
