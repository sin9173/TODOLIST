package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDeleteRequestVO {

    @ApiModelProperty(notes = "비밀번호", example = "123", position = 0)
    private String user_pw;
}
