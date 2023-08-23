package yemenshi.gsp.todo_list.service.common;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yemenshi.gsp.todo_list.domain.common.DTO.MemberDTO;
import yemenshi.gsp.todo_list.domain.common.Member;
import yemenshi.gsp.todo_list.repository.common.MemberRepository;

import java.util.Optional;

@Service
@Slf4j
public class MemberService {
  private final MemberRepository memberRepository;

  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Transactional
  public MemberDTO login(MemberDTO memberDTO) {
    Optional<Member> byMemberEmail = memberRepository.findByEmail(memberDTO.getMemberEmail());
    if (byMemberEmail.isPresent()) { // 중복된 이메일이 있으므로 null 반환
      log.debug("not login");
      return null;
    } else { // 중복된 이메일이 없으면 해당 계정으로 회원가입.
      Member member = new Member(memberDTO);
      memberRepository.save(member);
      return memberDTO;
    }
  }

  @Transactional(readOnly = true)
  public String emailCheck(String memberEmail) {
    Optional<Member> byMemberEmail = memberRepository.findByEmail(memberEmail);
    return byMemberEmail.isEmpty() ? null : "ok";
  }
}
