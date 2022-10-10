package com.moais.todo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "todo.work")
@SequenceGenerator(
        name = "WORK_SEQ_GENERATOR",
        catalog = "todo",
        sequenceName = "WORK_SEQ",
        initialValue = 1, allocationSize = 200
)
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WORK_SEQ_GENERATOR")
    private Long id; //인덱스

    private String content; //할일 내용

    @Enumerated(EnumType.STRING)
    private WorkState state = WorkState.할일; //상태(할일, 진행중, 완료됨)

    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now(); //등록일시

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now(); //수정일시


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //회원


    public Work() {

    }

    public Work(String content, Member member) {
        this.content = content;
        this.member = member;
    }
}
