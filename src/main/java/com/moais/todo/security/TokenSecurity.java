package com.moais.todo.security;

public interface TokenSecurity {

    public String createToken(String subject, long ttlMillis);

    public String getSubject(String token);
}
