package com.moais.todo.module;

import com.moais.todo.util.AES256SEC;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AESTest {

    @Autowired
    private AES256SEC aes256SEC;

    @Test
    public void encTest() {
        String a = "aaaa";
        String b = aes256SEC.encrypt(a);

        System.out.println(b);

        System.out.println(aes256SEC.decrypt(b));
        Assertions.assertThat(aes256SEC.decrypt(b).equals(a));
    }



}
