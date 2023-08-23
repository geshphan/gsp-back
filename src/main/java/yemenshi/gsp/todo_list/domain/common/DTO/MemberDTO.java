package yemenshi.gsp.todo_list.domain.common.DTO;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemberDTO {

  private Long id;

  private String memberEmail;


  private String memberName;
}

