package com.moais.todo.service;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.MemberInfo;
import com.moais.todo.entity.Token;
import com.moais.todo.repository.MemberInfoRepository;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.repository.TokenRepository;
import com.moais.todo.security.Encryption;
import com.moais.todo.security.TokenSecurity;
import com.moais.todo.security.AES256SEC;
import com.moais.todo.util.RequestUtil;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import com.moais.todo.vo.request.*;
import com.moais.todo.vo.response.MemberLoginVO;
import com.moais.todo.vo.response.TokenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final TokenRepository tokenRepository;

    private final Encryption encryption;

    private final TokenSecurity tokenSecurity;

    @Override
    public ResponseVO memberRegister(MemberRegisterRequestVO vo) { //회원 등록
        MemberInfo memberInfo = new MemberInfo(vo.getNick_name());
        memberInfoRepository.save(memberInfo);
        String salt = encryption.getSalt();
        vo.setUser_pw(encryption.encrypt(vo.getUser_pw(), salt));
        Member member = new Member(vo, memberInfo, salt);
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
    public ResponseVO memberLogin(MemberLoginRequestVO vo, HttpServletResponse response) { //회원 로그인
        Member member = memberRepository.findByUserId(vo.getUser_id());
        if(member==null) return new ResponseVO(ResultCode.LOGIN_ERROR, "존재하지 않는 아이디입니다.");
        if(!(member.getUserPw()).equals(encryption.encrypt(vo.getUser_pw(), member.getSalt())))
            return new ResponseVO(ResultCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다.");

        String token = tokenSecurity.createToken(member.getUserId(), (1000 * 60 * 10));
        String refresh_token = tokenSecurity.createToken("TODO", (1000 * 60 * 60 * 24));

        Token tokenEntity = new Token(refresh_token, token);
        tokenRepository.save(tokenEntity);

        //Cookie
        Cookie token_cookie = new Cookie("token", token);
        token_cookie.setMaxAge(1000 * 60 * 10);
        token_cookie.setPath("/");
        token_cookie.setHttpOnly(true);

        Cookie refresh_token_cookie = new Cookie("refresh_token", refresh_token);
        refresh_token_cookie.setMaxAge(1000 * 60 * 60 * 24);
        refresh_token_cookie.setPath("/");
        refresh_token_cookie.setHttpOnly(true);

        response.addCookie(token_cookie);
        response.addCookie(refresh_token_cookie);

        return new MemberLoginVO(member, token, refresh_token);
    }

    @Override
    public ResponseVO tokenRefresh(String refresh_token, String access_token, String user_id) {
        if(refresh_token==null) refresh_token = RequestUtil.getCookieParam("refresh_token");
        if(access_token==null) access_token = RequestUtil.getCookieParam("token");

        Token tokenEntity = tokenRepository.findByRefreshTokenEqualsAndAccessTokenEquals(refresh_token, access_token);

        if(tokenEntity==null) return new ResponseVO(ResultCode.LOGIN_ERROR, "유효하지 않은 토큰입니다.");
        String tokenCheck = tokenSecurity.getSubject(refresh_token);
        if(tokenCheck.equals("")) {
            tokenRepository.delete(tokenEntity);
            return new ResponseVO(ResultCode.LOGIN_ERROR, "유효하지 않은 토큰입니다.");
        }

        String token = tokenSecurity.createToken(user_id, (1000 * 60 * 10));

        tokenEntity.setAccessToken(token);
        tokenRepository.save(tokenEntity);

        //Cookie
        Cookie token_cookie = new Cookie("token", token);
        token_cookie.setMaxAge(1000 * 60 * 10);
        token_cookie.setPath("/");
        token_cookie.setHttpOnly(true);

        return new TokenVO(token);
    }

    @Override
    public ResponseVO memberPasswordModify(MemberPasswordModifyRequestVO vo, String user_id) { //비밀번호 변경
        if(vo.getUser_pw().equals("")) return new ResponseVO(ResultCode.NULL_ERROR, "비밀번호를 빈값으로 설정할 수 없습니다.");
        Member member = memberRepository.findByUserId(user_id);
        if(!(member.getUserPw()).equals(encryption.encrypt(vo.getUser_pw(), member.getSalt()))) return new ResponseVO(ResultCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다.");
        member.setSalt(encryption.getSalt());
        member.setUserPw(encryption.encrypt(vo.getNew_user_pw(), member.getSalt()));
        memberRepository.save(member);
        return new ResponseVO();
    }

    @Override
    public ResponseVO memberInfoModify(MemberInfoModifyRequestVO vo, String user_id) { // 회원정보 변경
        MemberInfo memberInfo = memberRepository.findByUserId(user_id).getMemberInfo();
        if(vo.getNick_name()!=null) memberInfo.setNickName(vo.getNick_name());
        memberInfoRepository.save(memberInfo);
        return new ResponseVO();
    }

    @Override
    public ResponseVO memberDelete(MemberDeleteRequestVO vo, String user_id) { //회원 탈퇴
        Member member = memberRepository.findByUserId(user_id);
        if(!(member.getUserPw()).equals(encryption.encrypt(vo.getUser_pw(), member.getSalt()))) return new ResponseVO(ResultCode.LOGIN_ERROR, "비밀번호가 일치하지 않습니다.");
        memberInfoRepository.delete(member.getMemberInfo());
        memberRepository.delete(member);
        return new ResponseVO();
    }
}
