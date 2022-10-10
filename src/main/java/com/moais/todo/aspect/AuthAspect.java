package com.moais.todo.aspect;

import com.moais.todo.entity.Member;
import com.moais.todo.log.ExceptionLog;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.security.TokenSecurity;
import com.moais.todo.util.RequestUtil;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Order(value = 3)
@RequiredArgsConstructor
public class AuthAspect {

    private final TokenSecurity tokenSecurity;

    private final MemberRepository memberRepository;

    @Around("execution(public * com.moais.todo.controller.*.*(..))" + "&& @annotation(com.moais.todo.annotation.TokenCheck)")
    public Object token_check(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = RequestUtil.getRequest();

        //토큰체크
        String token = request.getHeader("token");
        if(token == null || token.equals("")) return new ResponseVO(ResultCode.LOGIN_ERROR, "로그인 해주세요.");
        String tokenCheck = tokenSecurity.getSubject(token);
        if(tokenCheck.equals("")) return new ResponseVO(ResultCode.LOGIN_ERROR, "유효하지 않은 토큰입니다.");

        //회원체크
        try {
            Member member = memberRepository.findByUserId(tokenCheck);
            if(member==null) return new ResponseVO(ResultCode.LOGIN_ERROR, "가입되지 않은 회원입니다.");
            return pjp.proceed(pjp.getArgs());
        } catch(Exception e) {
            e.printStackTrace();
            new ExceptionLog(e, tokenCheck).logging();
            return new ResponseVO(ResultCode.ERROR_CODE, "에러");
        }
    }
}
