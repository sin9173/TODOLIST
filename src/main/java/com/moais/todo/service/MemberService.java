package com.moais.todo.service;

import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;

import javax.servlet.http.HttpServletResponse;

public interface MemberService {

    public ResponseVO memberRegister(MemberRegisterRequestVO vo); //회원 등록

    public ResponseVO memberIdCheck(MemberIdCheckRequestVO vo);

    public ResponseVO memberLogin(MemberLoginRequestVO vo, HttpServletResponse response); //회원 로그인

    public ResponseVO memberLogout(HttpServletResponse response);

    public ResponseVO tokenRefresh(String refresh_token, String access_token, String user_id, HttpServletResponse response);

    public ResponseVO memberPasswordModify(MemberPasswordModifyRequestVO vo, String user_id); //회원 비밀번호 수정

    public ResponseVO memberInfoModify(MemberInfoModifyRequestVO vo, String user_id); //회원 정보 수정

    public ResponseVO memberDelete(MemberDeleteRequestVO vo, String user_id); //회원 탈퇴
}