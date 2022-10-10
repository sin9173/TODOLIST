package com.moais.todo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "todo.member_info")
@Getter
@Setter
@ToString
public class MemberInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //인덱스

    @Column(name = "nick_name")
    private String nickName; //닉네임

    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now(); //등록일시

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now(); //수정일시

    public MemberInfo() {

    }

    public MemberInfo(String nickName) {
        this.nickName = nickName;
    }
}
