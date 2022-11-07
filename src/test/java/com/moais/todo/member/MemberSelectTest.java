package com.moais.todo.member;

import com.moais.todo.entity.Member;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.service.MemberService;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import com.moais.todo.vo.request.MemberLoginRequestVO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberSelectTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void selectAllTest() {
        List<Member> members = memberRepository.findAll();
        System.out.println(members);
    }

    @Test
    public void selectTest() {
        Member member = memberRepository.findByUserId("ccc");
        Assertions.assertThat(member.getUserId().equals("ccc"));
    }

    @Test
    public void loginTest() {
        MemberLoginRequestVO vo = new MemberLoginRequestVO();
        vo.setUser_id("ccc");
        vo.setUser_pw("123456");
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        ResponseVO response = memberService.memberLogin(vo, httpServletResponse);
        Assertions.assertThat(response.getResult()).isEqualTo(ResultCode.SUCCESS_CODE);
    }
}
