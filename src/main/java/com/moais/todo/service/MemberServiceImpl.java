package com.moais.todo.service;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.MemberInfo;
import com.moais.todo.repository.MemberInfoRepository;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.security.TokenSecurity;
import com.moais.todo.util.AES256SEC;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import com.moais.todo.vo.request.*;
import com.moais.todo.vo.response.MemberLoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;

    private final AES256SEC aes256SEC;

    private final TokenSecurity tokenSecurity;

    @Override
    public ResponseVO memberRegister(MemberRegisterRequestVO vo) { //회원 등록
        MemberInfo memberInfo = new MemberInfo(vo.getNick_name());
        memberInfoRepository.save(memberInfo);
        vo.setUser_pw(aes256SEC.encrypt(vo.getUser_pw()));
        Member member = new Member(vo, memberInfo);
        memberRepository.save(member);
        return new ResponseVO();
    }

    @Override
    public ResponseVO memberIdCheck(MemberIdCheckRequestVO vo) { //아이디 중복 체크
        if(memberRepository.findByUserId(vo.getUser_id())==null)
            return new ResponseVO(ResultCode.SUCCESS_CODE, "사용가능한 아이디입니다.");
        else return new ResponseVO(ResultCode.DUPLICATE_ERROR, "이미 사용중인 아이디입니다.");
    }

    @Override
    public ResponseVO memberLogin(MemberLoginRequestVO vo) { //회원 로그인
        Member member = memberRepository.findByUserId(vo.getUser_id());
        if(member==null) return new ResponseVO(ResultCode.LOGIN_ERROR, "존재하지 않는 아이디입니다.");
        if(!aes256SEC.decrypt(member.getUserPw()).equals(vo.getUser_pw()))
            return new ResponseVO(ResultCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다.");
        return new MemberLoginVO(member, tokenSecurity.createToken(member.getUserId(), (1000 * 60 * 60 * 24)));
    }


    @Override
    public ResponseVO memberPasswordModify(MemberPasswordModifyRequestVO vo, String token) { //비밀번호 변경
        if(vo.getUser_pw().equals("")) return new ResponseVO(ResultCode.NULL_ERROR, "비밀번호를 빈값으로 설정할 수 없습니다.");
        Member member = memberRepository.findByUserId(tokenSecurity.getSubject(token));
        if(!aes256SEC.decrypt(member.getUserPw()).equals(vo.getUser_pw())) return new ResponseVO(ResultCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다.");
        member.setUserPw(aes256SEC.encrypt(vo.getNew_user_pw()));
        memberRepository.save(member);
        return new ResponseVO();
    }

    @Override
    public ResponseVO memberInfoModify(MemberInfoModifyRequestVO vo, String token) { // 회원정보 변경
        MemberInfo memberInfo = memberRepository.findByUserId(tokenSecurity.getSubject(token)).getMemberInfo();
        if(vo.getNick_name()!=null) memberInfo.setNickName(vo.getNick_name());
        memberInfoRepository.save(memberInfo);
        return new ResponseVO();
    }

    @Override
    public ResponseVO memberDelete(MemberDeleteRequestVO vo, String token) { //회원 탈퇴
        Member member = memberRepository.findByUserId(tokenSecurity.getSubject(token));
        if(!aes256SEC.decrypt(member.getUserPw()).equals(vo.getUser_pw())) return new ResponseVO(ResultCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다.");
        memberInfoRepository.delete(member.getMemberInfo());
        memberRepository.delete(member);
        return new ResponseVO();
    }
}
