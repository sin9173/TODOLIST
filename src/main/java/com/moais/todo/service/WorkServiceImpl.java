package com.moais.todo.service;

import com.moais.todo.entity.Member;
import com.moais.todo.entity.Work;
import com.moais.todo.repository.MemberRepository;
import com.moais.todo.repository.WorkRepository;
import com.moais.todo.security.TokenSecurity;
import com.moais.todo.vo.ResponseVO;
import com.moais.todo.vo.ResultCode;
import com.moais.todo.vo.request.*;
import com.moais.todo.vo.response.WorkListVO;
import com.moais.todo.vo.response.WorkRecentVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkServiceImpl implements WorkService {

    private final WorkRepository workRepository;

    private final MemberRepository memberRepository;

    private final TokenSecurity tokenSecurity;

    @Override
    public ResponseVO workRegister(WorkRegisterRequestVO vo, String token) { //할일 등록
        Member member = memberRepository.findByUserId(tokenSecurity.getSubject(token));
        Work work = new Work(vo.getContent(), member);
        workRepository.save(work);
        return new ResponseVO();
    }

    @Override
    public ResponseVO workList(WorkListRequestVO vo, String token) { //할일 리스트 조회
        Member member = memberRepository.findByUserId(tokenSecurity.getSubject(token));
        Page<Work> data = workRepository.findByMemberOrderByIdDesc(member, PageRequest.of(vo.getPage()==0?(vo.getPage()):(vo.getPage()-1), 10));
        return new WorkListVO(data);
    }

    @Override
    public ResponseVO workRecent(String token) { //할일 최근 데이터 조회
        Member member = memberRepository.findByUserId(tokenSecurity.getSubject(token));
        Work work = workRepository.findFirstByMemberOrderByRegDateDesc(member);
        if(work==null) return new ResponseVO(ResultCode.NULL_ERROR, "데이터없음");
        return new WorkRecentVO(work);
    }

    @Override
    public ResponseVO workModify(WorkModifyRequestVO vo) { //할일 수정
        Optional<Work> workOptional = workRepository.findById(vo.getId());
        if(workOptional==null) return new ResponseVO(ResultCode.NULL_ERROR, "존재하지 않는 할일입니다.");
        Work work = workOptional.get();
        work.setContent(vo.getContent());
        workRepository.save(work);
        return new ResponseVO();
    }

    @Override
    public ResponseVO workModifyState(WorkModifyStateRequestVO vo) { //할일 상태 수정
        Optional<Work> workOptional = workRepository.findById(vo.getId());
        if(workOptional==null) return new ResponseVO(ResultCode.NULL_ERROR, "존재하지 않는 할일입니다.");
        Work work = workOptional.get();
        work.setState(vo.getState());
        workRepository.save(work);
        return new ResponseVO();
    }

    @Override
    public ResponseVO workDelete(WorkDeleteRequestVO vo, String token) {
        Optional<Work> workOptional = workRepository.findById(vo.getId());
        if(workOptional==null) return new ResponseVO(ResultCode.NULL_ERROR, "존재하지 않는 할일입니다.");
        Work work = workOptional.get();
        if(!work.getMember().getUserId().equals(tokenSecurity.getSubject(token)))
            return new ResponseVO(ResultCode.LOGIN_ERROR, "게시자의 아이디와 로그인한 계정이 다릅니다.");
        workRepository.delete(work);
        return new ResponseVO();
    }
}
