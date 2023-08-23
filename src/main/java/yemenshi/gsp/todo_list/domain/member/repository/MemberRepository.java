package yemenshi.gsp.todo_list.domain.member.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import yemenshi.gsp.todo_list.domain.member.entity.Member;

import java.util.Optional;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Optional<Member> findMemberByLoginId(String loginId) throws NoResultException {
        Member member = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getSingleResult();

        return Optional.of(member);
    }

    public void persist(Member member) {
        em.persist(member);
    }
}
