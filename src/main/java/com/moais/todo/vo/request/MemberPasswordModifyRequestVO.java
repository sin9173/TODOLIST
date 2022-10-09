package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberPasswordModifyRequestVO {
    @ApiModelProperty(notes = "변경할 비밀번호", example = "123", required = true, position = 0)
    private String user_pw;
}
