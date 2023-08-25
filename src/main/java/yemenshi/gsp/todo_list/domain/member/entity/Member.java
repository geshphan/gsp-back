package yemenshi.gsp.todo_list.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String loginId;

    protected Member() {}

    public Member(MemberDto memberDto) {
        this.id = memberDto.getId();
        this.loginId = memberDto.getLoginId();
    }

    public Member(String loginId) {
        this.loginId = loginId;
    }
}
