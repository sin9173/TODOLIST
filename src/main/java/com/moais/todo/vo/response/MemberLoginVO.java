package com.moais.todo.vo.response;

import com.moais.todo.entity.Member;
import com.moais.todo.vo.ResponseVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberLoginVO extends ResponseVO { //로그인 응답데이터

    @ApiModelProperty(notes = "결과코드", example = "00", position = 0)
    private String result = "00";

    @ApiModelProperty(notes = "결과메세지", example = "성공", position = 1)
    private String message = "성공";

    @ApiModelProperty(notes = "아이디", example = "id123", position = 2)
    private String user_id;

    @ApiModelProperty(notes = "닉네임", example = "닉네임", position = 3)
    private String nick_name;

    @ApiModelProperty(notes = "토큰", example = "", position = 4)
    private String token;

    @ApiModelProperty(notes = "refresh 토큰", example = "", position = 5)
    private String refresh_token;

    public MemberLoginVO(Member member, String token, String refresh_token) {
        this.user_id = member.getUserId();
        this.nick_name = member.getMemberInfo().getNickName();
        this.token = token;
        this.refresh_token = refresh_token;
    }
}
