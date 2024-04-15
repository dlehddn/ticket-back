package ticketing.ticket.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ticketing.ticket.member.domain.dto.JwtTokenDto;
import ticketing.ticket.member.domain.dto.LoginDto;
import ticketing.ticket.member.domain.dto.MemberDto;
import ticketing.ticket.member.service.MemberService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public void setMember (@RequestBody MemberDto memberDto) {
        memberService.signUp(memberDto);
    }
    
    @PostMapping("/signin")
    public ResponseEntity<JwtTokenDto> signIn(@RequestBody LoginDto loginDto){
      JwtTokenDto  jwtToken =  memberService.signIn(loginDto.getEmail(), loginDto.getPassword());

      return ResponseEntity.ok(jwtToken);
    }


}
