package yemenshi.gsp.todo_list.domain.common.DTO;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {

  private Long id;

  @Column(name = "member_email")
  private String memberEmail;

  @Column(name = "member_name")
  private String memberName;
}

