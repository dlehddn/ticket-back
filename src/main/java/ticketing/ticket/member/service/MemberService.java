package ticketing.ticket.member.service;

import ticketing.ticket.member.domain.dto.JwtTokenDto;
import ticketing.ticket.member.domain.dto.MemberDto;

public interface MemberService {
    void signUp(MemberDto memberDto);
    JwtTokenDto signIn(String username, String password);
}
