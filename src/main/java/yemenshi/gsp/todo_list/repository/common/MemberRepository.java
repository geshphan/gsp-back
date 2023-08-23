package yemenshi.gsp.todo_list.repository.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import yemenshi.gsp.todo_list.domain.common.DTO.MemberDTO;
import yemenshi.gsp.todo_list.domain.common.Member;

import java.util.Optional;

@Repository
public class MemberRepository {
  @PersistenceContext
  private EntityManager entityManager;
  public Optional<Member> findByEmail(String email) {
    String queryString = "SELECT m FROM Member m WHERE m.memberEmail = :email";
    return entityManager.createQuery(queryString, Member.class)
            .setParameter("email", email)
            .getResultList()
            .stream()
            .findFirst();
  }

  public void save(Member member) {
    entityManager.persist(member);
    entityManager.flush();
    entityManager.clear();
  }
}
