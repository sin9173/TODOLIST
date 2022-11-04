package com.moais.todo.security;

public interface Encryption {

    public String encrypt(String plainText, String salt);

    public String decrypt(String cipherText);

    public String getSalt();
}
