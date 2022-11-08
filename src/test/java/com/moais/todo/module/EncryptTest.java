package com.moais.todo.module;

import com.moais.todo.security.Encryption;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EncryptTest {

    @Autowired
    Encryption encryption;

    @Test
    public void encryptTest() {
        String plain = "abcde";
        String plain2 = "abcde";
        String salt = encryption.getSalt();
        String result = encryption.encrypt(plain, salt);
        String result2 = encryption.encrypt(plain2, salt);

        System.out.println("salt : " + salt);

        System.out.println("result : " + result);

        Assertions.assertThat(result).isEqualTo(result2);
    }
}
