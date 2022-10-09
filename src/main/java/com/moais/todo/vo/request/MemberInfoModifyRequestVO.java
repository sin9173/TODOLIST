package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberInfoModifyRequestVO {

    @ApiModelProperty(notes = "닉네임", example = "", position = 0)
    private String nick_name;
}
