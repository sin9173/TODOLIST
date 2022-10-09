package com.moais.todo.member;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.MemberInfo;
import com.moais.todo.repository.MemberInfoRepository;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.service.MemberService;
import com.moais.todo.vo.request.MemberRegisterRequestVO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MemberSaveTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberInfoRepository memberInfoRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void saveTest() {
        Member member = new Member();
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setNickName("테스트");
        memberInfoRepository.save(memberInfo);
        member.setUserId("bbb");
        member.setUserPw("1111");
        member.setMemberInfo(memberInfo);
        memberRepository.save(member);

    }

    @Test
    public void modifyNickNameTest() {
        Long id = 2L;
        Member member = memberRepository.findById(id).get();
        System.out.println(member);
        member.getMemberInfo().setNickName("닉네임변경1");
        memberInfoRepository.save(member.getMemberInfo());
    }

    @Test
    public void memberRegisterTest() {
        MemberRegisterRequestVO vo = new MemberRegisterRequestVO();
        vo.setUser_id("eee");
        vo.setPassword("123");
        vo.setNick_name("ㅁㅁㅁㅁ");
        memberService.memberRegister(vo);
    }


}
