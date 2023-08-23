package yemenshi.gsp.todo_list.controller.common;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yemenshi.gsp.todo_list.domain.common.DTO.MemberDTO;
import yemenshi.gsp.todo_list.service.common.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Slf4j
public class MemberController {
  //생성자 주입
  private final MemberService memberService;


  /**
   * @url POST /api/member/login
   * @param *memberEmail
   * @param *memberName
   */
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO) {
    log.debug("Attempting to login");
    MemberDTO result = memberService.login(memberDTO);
    if (result == null){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
    } else{

      return ResponseEntity.ok("Login Successful");
    }
  }

  /**
   * @url /api/member/email-check
   * @param *memberEmail
   */
  @PostMapping("/email-check")
  public ResponseEntity<String>  emailCheck(@RequestBody String memberEmail) {
    log.debug("memberEmail = " + memberEmail);
    String checkResult = memberService.emailCheck(memberEmail);
    if(checkResult == null){
      return ResponseEntity.ok("Email already exists");
    }else{
      return ResponseEntity.ok("ok");
    }
  }

}
