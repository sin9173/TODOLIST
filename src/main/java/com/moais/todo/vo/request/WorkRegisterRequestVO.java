package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkRegisterRequestVO { //할일 등록 요청데이터
    @ApiModelProperty(notes = "content", example = "수학공부", position = 0)
    private String content;
}
