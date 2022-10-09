package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberLoginRequestVO { //로그인 요청데이터

    @ApiModelProperty(notes = "아이디", example = "aaa", required = true, position = 0)
    private String user_id; //아이디

    @ApiModelProperty(notes = "비밀번호", example = "132", required = true, position = 1)
    private String user_pw; //비밀번호
}
