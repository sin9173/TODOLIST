package com.moais.todo.module;

import com.moais.todo.security.TokenSecurity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TokenTest {

    @Autowired
    private TokenSecurity tokenSecurity;

    @Test
    public void token_check_test() {
        String a = "123";
        String token = tokenSecurity.createToken(a, 1000 * 60);

        String subject = tokenSecurity.getSubject(token);

        Assertions.assertThat(a).isEqualTo(subject);

    }
}
