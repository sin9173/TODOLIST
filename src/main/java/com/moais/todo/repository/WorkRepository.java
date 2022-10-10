package com.moais.todo.repository;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    public List<Work> findByMember(Member member);
    public Work findFirstByMemberOrderByRegDateDesc(Member member);

    public Page<Work> findByMemberOrderByIdDesc(Member member, Pageable pageable);
}
