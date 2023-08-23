package yemenshi.gsp.todo_list.domain.member.service;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yemenshi.gsp.todo_list.domain.member.entity.Member;
import yemenshi.gsp.todo_list.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository dao;

    public Member login(String loginId) {
        Member member;

        try {
            member = dao.findMemberByLoginId(loginId).get();

        } catch (NoResultException e) {
            log.info("Signing up a new member with the login id : {}", loginId);
            dao.persist(new Member(loginId));

            member = dao.findMemberByLoginId(loginId).get();
        }

        return member;
    }
}
