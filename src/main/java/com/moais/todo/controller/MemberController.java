package com.moais.todo.controller;

import com.moais.todo.annotation.TokenCheck;
import com.moais.todo.service.MemberService;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;
import com.moais.todo.vo.response.MemberLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "회원 API", description = "회원 API")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @ApiOperation(value = "회원등록", notes = "회원을 등록합니다.", response = ResponseVO.class)
    @PostMapping("/member/register")
    public ResponseVO memberRegister(@RequestBody MemberRegisterRequestVO data) {
        return memberService.memberRegister(data);
    }

    @ApiOperation(value = "로그인", notes = "로그인을 합니다.", response = MemberLoginVO.class)
    @PostMapping("/member/login")
    public ResponseVO memberLogin(@RequestBody MemberLoginRequestVO data) {
        return memberService.memberLogin(data);
    }

    @TokenCheck
    @ApiOperation(value = "회원 비밀번호 변경", notes = "비밀번호를 변경합니다." , response = ResponseVO.class)
    @PostMapping("/member/modify/password")
    public ResponseVO memberModifyPassword(@RequestBody MemberPasswordModifyRequestVO data, @RequestHeader("token") String token) {
        return memberService.memberPasswordModify(data, token);
    }

    @TokenCheck
    @ApiOperation(value = "회원 정보 변경", notes = "회원정보를 변경합니다.", response = ResponseVO.class)
    @PostMapping("/member/modify/info")
    public ResponseVO memberModifyInfo(@RequestBody MemberInfoModifyRequestVO data, @RequestHeader("token") String token) {
        return memberService.memberInfoModify(data, token);
    }

    @TokenCheck
    @ApiOperation(value = "회원 탈퇴", notes = "회원탈퇴를 합니다.", response = ResponseVO.class)
    @PostMapping("/member/delete")
    public ResponseVO memberDelete(@RequestBody MemberDeleteRequestVO data, @RequestHeader("token") String token) {
        return memberService.memberDelete(data, token);
    }

}
