package com.moais.todo.vo.response;

import com.moais.todo.entity.Work;
import com.moais.todo.entity.WorkState;
import com.moais.todo.vo.ResponseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class WorkRecentVO extends ResponseVO { //최근 할일 데이터

    @ApiModelProperty(notes = "결과코드", example = "00", position = 0)
    private String result = "00";

    @ApiModelProperty(notes = "결과메세지", example = "성공", position = 1)
    private String message = "성공";

    @ApiModelProperty(notes = "인덱스", example = "1", position = 0)
    private Long id;

    @ApiModelProperty(notes = "아이디", example = "aaa", position = 1)
    private String user_id;

    @ApiModelProperty(notes = "닉네임", example = "닉네임", position = 2)
    private String nick_name;

    @ApiModelProperty(notes = "할일", example = "산책", position = 3)
    private String content;

    @ApiModelProperty(notes = "상태[할일, 진행중, 완료됨]", example = "할일", position = 4)
    private WorkState state;

    @ApiModelProperty(notes = "등록일시", example = "2022-10-09 20:00:00", position = 5)
    private String reg_date;

    @ApiModelProperty(notes = "수정일시", example = "2022-10-09 20:00:00", position = 6)
    private String update_date;

    public WorkRecentVO(Work work) {
        this.id = work.getId();
        this.user_id = work.getMember().getUserId();
        this.nick_name = work.getMember().getMemberInfo().getNickName();
        this.content = work.getContent();
        this.state = work.getState();
        this.reg_date = work.getRegDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.update_date = work.getUpdateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
