package com.moais.todo.work;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.Work;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.repository.WorkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WorkSaveTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    WorkRepository workRepository;

    @Test
    public void workRegisterTest() {
        Member member = memberRepository.findByUserId("aaa");
        Work work = new Work("안녕할일2", member);
        workRepository.save(work);
    }
}
