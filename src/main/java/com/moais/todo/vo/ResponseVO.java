package com.moais.todo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseVO {

    @ApiModelProperty(notes = "결과코드", example = "00", position = 0)
    private String result;

    @ApiModelProperty(notes = "결과메세지", example = "성공", position = 1)
    private String message;

    public ResponseVO(String result, String message) {
        this.result = result;
        this.message = message;
    }

    public ResponseVO() {
        this.result = ResultCode.SUCCESS_CODE;
        this.message = "성공";
    }


}
