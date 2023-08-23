package yemenshi.gsp.todo_list.domain.common;

import jakarta.persistence.*;
import yemenshi.gsp.todo_list.domain.common.DTO.MemberDTO;

@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, name = "member_email")
  private String memberEmail;

  @Column(name = "member_name")
  private String memberName;

  public Member(MemberDTO dto) {
    this.memberEmail = dto.getMemberEmail();
    this.memberName = dto.getMemberName();
  }

  public Member() {}
}
