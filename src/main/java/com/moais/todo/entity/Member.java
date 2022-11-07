package com.moais.todo.entity;

import com.moais.todo.vo.request.MemberRegisterRequestVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "todo.member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //인덱스

    @Column(name = "user_id")
    private String userId; //회원 아이디

    @Column(name = "user_pw")
    private String userPw; //회원 비밀번호

    @OneToOne
    @JoinColumn(name = "member_info_id")
    private MemberInfo memberInfo; //회원정보

    @Column(name = "salt")
    private String salt; //비밀번호 salt

    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now(); //등록일시

    @Column(name = "update_date")
    private LocalDateTime updateDate = LocalDateTime.now(); //수정일시

    public Member() {

    }

    public Member(MemberRegisterRequestVO vo, MemberInfo memberInfo, String salt) {
        this.userId = vo.getUser_id();
        this.userPw = vo.getUser_pw();
        this.memberInfo = memberInfo;
        this.salt = salt;
    }

}
