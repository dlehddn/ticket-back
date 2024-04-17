package ticketing.ticket.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ticketing.ticket.member.domain.dto.JwtTokenDto;
import ticketing.ticket.member.domain.dto.LogInDto;
import ticketing.ticket.member.domain.dto.SignUpDto;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody SignUpDto signUpDto) {
        memberService.saveMember(signUpDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<Member> signIn(@RequestBody LogInDto logInDto) {
        return memberService.signIn(logInDto.getEmail(), logInDto.getPassword());
    }
}
