package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberRegisterRequestVO {

    @ApiModelProperty(notes = "회원아이디", example = " ids123", required = true, position = 0)
    private String user_id;

    @ApiModelProperty(notes = "비밀번호", example = "12345", required = true, position = 1)
    private String user_pw;

    @ApiModelProperty(notes = "닉네임", example = "닉네임", required = true, position = 2)
    private String nick_name;

}
