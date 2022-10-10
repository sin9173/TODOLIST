package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkDeleteRequestVO {

    @ApiModelProperty(notes = "인덱스", example = "1", position = 0)
    private Long id;
}
