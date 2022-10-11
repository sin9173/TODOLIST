package com.moais.todo.work;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.Work;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.repository.WorkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WorkSelectTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WorkRepository workRepository;

    @Test
    public void workSelectTest() {
        Member member = memberRepository.findByUserId("test01");
        List<Work> list = workRepository.findByMember(member);
        //System.out.println(list);
    }

    @Test
    public void workSelectFirstTest() {
        Member member = memberRepository.findByUserId("test01");
        Work work = workRepository.findFirstByMemberOrderByRegDateDesc(member);
//        System.out.println(work);
    }

    @Test
    public void workSelectPageTest() {
        Member member = memberRepository.findByUserId("test01");
        Page<Work> data = workRepository.findByMemberOrderByIdDesc(member, PageRequest.of(3, 5));
//        System.out.println(data.getTotalElements()); //총 ROW 수
//        System.out.println(data.getTotalPages()); //총페이지
//        System.out.println(data.getNumber()); // 페이지
//        System.out.println(data.getNumberOfElements()); // 조회된 ROW 수
//        System.out.println(data.getSize()); //조회개수
//        System.out.println(data.getPageable());
    }
}
