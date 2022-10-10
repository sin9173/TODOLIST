package com.moais.todo.vo.request;

import com.moais.todo.entity.WorkState;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WorkModifyStateRequestVO { //할일 상태 수정 요청데이터

    @ApiModelProperty(notes = "인덱스", example = "1", required = true, position = 0)
    private Long id;

    @ApiModelProperty(notes = "상태", example = "진행중", required = true, position = 1)
    private WorkState state;
}
