package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkModifyRequestVO { // 할일 수정 요청데이터

    @ApiModelProperty(notes = "인덱스", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "내용", example = "운동", required = true, position = 0)
    private String content;

}
