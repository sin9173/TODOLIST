package com.moais.todo.service;

import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.request.*;

public interface MemberService {

    public ResponseVO memberRegister(MemberRegisterRequestVO vo); //회원 등록

    public ResponseVO memberLogin(MemberLoginRequestVO vo); //회원 로그인

    public ResponseVO memberPasswordModify(MemberPasswordModifyRequestVO vo, String token); //회원 비밀번호 수정

    public ResponseVO memberInfoModify(MemberInfoModifyRequestVO vo, String token); //회원 정보 수정

    public ResponseVO memberDelete(MemberDeleteRequestVO vo, String token); //회원 탈퇴
}