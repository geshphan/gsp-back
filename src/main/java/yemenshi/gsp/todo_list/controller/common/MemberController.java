package yemenshi.gsp.todo_list.controller.common;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yemenshi.gsp.todo_list.domain.common.DTO.MemberDTO;
import yemenshi.gsp.todo_list.service.common.MemberService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
  //생성자 주입
  private final MemberService memberService;

  @GetMapping("/login")
  public String saveForm() {
    return "redirect:/login.html";
  }

  @PostMapping("/login")
  public String save(@ModelAttribute MemberDTO memberDTO) {
    System.out.println("MemberController.login");
    System.out.println("memberDTO = " + memberDTO);
    MemberDTO result = memberService.login(memberDTO);
    if (result == null){
      return "redirect:/loginFailed.html";
    } else{
      return "redirect:/index.html";
    }
  }

  @PostMapping("/email-check")
  public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
    System.out.println("memberEmail = " + memberEmail);
    String checkResult = memberService.emailCheck(memberEmail);
    return checkResult;
  }

}
