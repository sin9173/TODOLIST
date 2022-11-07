package com.moais.todo.security;

import com.moais.todo.log.ExceptionLog;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class ShaEncrypt implements Encryption {

    @Override
    public String encrypt(String plainText, String salt) {
        byte[] hashBytes;
        int count = 3;
        String temp = plainText;
        try {
            for(int i=0 ; i<count ; i++) {
                temp.concat(salt);
                SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest384();
                hashBytes = digestSHA3.digest(temp.getBytes());
                temp = Base64.getEncoder().encodeToString(hashBytes);
                System.out.println("i : " + temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            new ExceptionLog(e, plainText).logging();
        }
        return temp;
    }

    @Override
    public String getSalt() {
        StringBuilder sb = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        byte[] temp = new byte[64];
        rnd.nextBytes(temp);

        for(byte a : temp) sb.append(String.format("%02x", a));

        return sb.toString();
    }

    @Override
    public String decrypt(String cipherText) {
        return null;
    }
}
