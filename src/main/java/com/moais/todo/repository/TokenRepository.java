package com.moais.todo.repository;

import com.moais.todo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    public Token findByRefreshTokenEqualsAndAccessTokenEquals(String refresh_token, String Access_token);
}
