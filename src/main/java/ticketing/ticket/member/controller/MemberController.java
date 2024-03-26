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
import ticketing.ticket.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/save")
    public void signUp(@RequestBody SignUpDto signUpDto) {
        memberService.saveMember(signUpDto);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenDto> signIn(@RequestBody LogInDto logInDto) {
        JwtTokenDto jwtToken = memberService.signIn(logInDto.getEmail(), logInDto.getPassword());
        return ResponseEntity.ok(jwtToken);
    }

}
