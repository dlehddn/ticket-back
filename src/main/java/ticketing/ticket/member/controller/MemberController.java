package ticketing.ticket.member.controller;

import lombok.RequiredArgsConstructor;
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
    public JwtTokenDto signIn(@RequestBody LogInDto logInDto) {
        String username = logInDto.getEmail();
        String password = logInDto.getPassword();
        JwtTokenDto jwtToken = memberService.signIn(username, password);
        return jwtToken;
    }

}
