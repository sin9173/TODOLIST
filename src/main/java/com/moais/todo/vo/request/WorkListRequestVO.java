package com.moais.todo.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkListRequestVO {

    @ApiModelProperty(value = "페이지", notes = "페이지", example = "1", position = 0)
    private int page = 0;

}
