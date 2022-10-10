package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberIdCheckRequestVO {

    @ApiModelProperty(notes = "회원아이디", example = "aaa", position = 0)
    private String user_id;
}
