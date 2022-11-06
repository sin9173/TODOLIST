package com.moais.todo.vo.response;

import com.moais.todo.vo.ResponseVO;
import lombok.Getter;

@Getter
public class TokenVO extends ResponseVO {
    private String access_token;

    public TokenVO(String access_token) {
        this.access_token = access_token;
    }
}
