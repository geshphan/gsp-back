package yemenshi.gsp.todo_list.domain.member;

import jakarta.persistence.NonUniqueResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yemenshi.gsp.todo_list.domain.member.entity.Member;
import yemenshi.gsp.todo_list.domain.member.entity.MemberDto;
import yemenshi.gsp.todo_list.domain.member.service.MemberServiceImpl;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberServiceImpl memberService;

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<Object> login(HttpServletRequest req, @RequestBody MemberDto memberDto) {
        HttpSession session = req.getSession();

        try {
            Member user = memberService.login(memberDto);
            session.setAttribute("user", user);

        } catch (NonUniqueResultException e) {
            return new ResponseEntity<>("An error occurred while searching user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Request has been proceeded successfully", HttpStatus.OK);
    }
}
