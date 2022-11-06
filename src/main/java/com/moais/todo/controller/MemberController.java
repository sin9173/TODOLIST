package com.moais.todo.controller;

import com.moais.todo.annotation.Nullable;
import com.moais.todo.annotation.TokenCheck;
import com.moais.todo.service.MemberService;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;
import com.moais.todo.vo.response.MemberLoginVO;
import com.moais.todo.vo.response.TokenVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Api(value = "회원 API", description = "회원 API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @ApiOperation(value = "회원등록", notes = "회원을 등록합니다.", response = ResponseVO.class)
    @PostMapping("/member")
    public ResponseVO memberRegister(@RequestBody MemberRegisterRequestVO data) {
        return memberService.memberRegister(data);
    }

    @ApiOperation(value = "아이디체크", notes = "아이디 중복을 체크합니다.", response = ResponseVO.class)
    @GetMapping("/member/idcheck/{user_id}")
    public ResponseVO memberIdCheck(@ModelAttribute MemberIdCheckRequestVO data) {
        return memberService.memberIdCheck(data);
    }

    @ApiOperation(value = "로그인", notes = "로그인을 합니다.", response = MemberLoginVO.class)
    @PostMapping("/member/login")
    public ResponseVO memberLogin(@RequestBody MemberLoginRequestVO data, HttpServletResponse response) {
        return memberService.memberLogin(data, response);
    }

    @Nullable
    @ApiOperation(value = "토큰 갱신", notes = "Access 토큰을 갱신합니다.", response = TokenVO.class)
    @GetMapping("/token/refresh")
    public ResponseVO tokenRefresh(
            @RequestHeader(value = "refreshToken", required = false) String refresh_token,
            @RequestHeader(value = "accessToken", required = false) String access_token,
            @RequestHeader(value = "userId") String user_id
            ) {
        return memberService.tokenRefresh(refresh_token, access_token, user_id);
    }

    @TokenCheck
    @ApiOperation(value = "회원 비밀번호 변경", notes = "비밀번호를 변경합니다." , response = ResponseVO.class)
    @PutMapping("/member/password")
    public ResponseVO memberModifyPassword(@RequestBody MemberPasswordModifyRequestVO data, @RequestHeader("userId") String user_id) {
        return memberService.memberPasswordModify(data, user_id);
    }

    @TokenCheck
    @ApiOperation(value = "회원 정보 변경", notes = "회원정보를 변경합니다.", response = ResponseVO.class)
    @PutMapping("/member/info")
    public ResponseVO memberModifyInfo(@RequestBody MemberInfoModifyRequestVO data, @RequestHeader("userId") String user_id) {
        return memberService.memberInfoModify(data, user_id);
    }

    @TokenCheck
    @ApiOperation(value = "회원 탈퇴", notes = "회원탈퇴를 합니다.", response = ResponseVO.class)
    @DeleteMapping("/member")
    public ResponseVO memberDelete(@RequestBody MemberDeleteRequestVO data, @RequestHeader("userId") String user_id) {
        return memberService.memberDelete(data, user_id);
    }

}
