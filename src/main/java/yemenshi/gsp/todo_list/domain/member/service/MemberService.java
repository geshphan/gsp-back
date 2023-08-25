package yemenshi.gsp.todo_list.domain.member.service;

import yemenshi.gsp.todo_list.domain.member.entity.Member;
import yemenshi.gsp.todo_list.domain.member.entity.MemberDto;

public interface MemberService {

    Member login(MemberDto memberDto);
}
