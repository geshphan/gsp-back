package yemenshi.gsp.todo_list.service.common;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Service;
import yemenshi.gsp.todo_list.domain.common.DTO.MemberDTO;
import yemenshi.gsp.todo_list.domain.common.Member;
import yemenshi.gsp.todo_list.repository.common.MemberRepository;

import java.util.Optional;

@Service
public class MemberService {
  private final MemberRepository memberRepository;
  private final EntityManagerFactory entityManagerFactory;

  public MemberService(MemberRepository memberRepository, EntityManagerFactory entityManagerFactory) {
    this.memberRepository = memberRepository;
    this.entityManagerFactory = entityManagerFactory;
  }

  public MemberDTO login(MemberDTO memberDTO) {
    Optional<Member> byMemberEmail = memberRepository.findByEmail(memberDTO.getMemberEmail());

    if (byMemberEmail.isPresent()) { // 중복된 이메일이 있으므로 null 반환
      System.out.println("not login");
      return null;
    } else { // 중복된 이메일이없으면 해당 계정으로 회원가입.
      EntityManager em = entityManagerFactory.createEntityManager();
      EntityTransaction tx = em.getTransaction();
      tx.begin();

      try{
        Member member = new Member();
        String memberName = memberDTO.getMemberName();
        member.setMemberEmail(memberDTO.getMemberEmail());
        member.setMemberName((memberName == null || memberName.isEmpty()) ? "" : memberName);
        em.persist(member);
        em.flush();
        em.clear();

        tx.commit();

        return memberDTO;
      } catch (Exception e) {
        tx.rollback();
        throw e;
      } finally {
        em.close();
      }
    }
  }


  public String emailCheck(String memberEmail) {
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    try {
      Optional<Member> byMemberEmail = em.createQuery(
                      "SELECT m FROM Member m WHERE m.memberEmail = :email", Member.class)
              .setParameter("email", memberEmail)
              .getResultList()
              .stream()
              .findFirst();

      tx.commit();
      return byMemberEmail.isPresent() ? null : "ok";
    } catch (Exception e) {
      tx.rollback();
      throw e;
    } finally {
      em.close();
    }

  }
}
