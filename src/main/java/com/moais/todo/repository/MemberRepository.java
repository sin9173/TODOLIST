package com.moais.todo.repository;

import com.moais.todo.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByUserId(String user_id);

}
