package ticketing.ticket.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ticketing.ticket.member.domain.dto.*;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody final SignUpDto signUpDto) {
        memberService.saveMember(signUpDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<MemberResponse> signIn(@RequestBody final LogInDto logInDto) {
        return memberService.signIn(logInDto.getEmail(), logInDto.getPassword());
    }
}
