package ticketing.ticket.member.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.stream.*;
import java.util.*;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ticketing.ticket.config.JwtTokenProvider;
import ticketing.ticket.member.domain.dto.JwtTokenDto;
import ticketing.ticket.member.domain.dto.MemberDto;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.member.service.MemberService;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void signUp(MemberDto memberDto) {
        Member member = Member.builder()
            .email(memberDto.getEmail())
            .name(memberDto.getName())
            .password(passwordEncoder.encode(memberDto.getPassword()))
            .roles(Collections.singletonList("USER"))
            .build();
       
        memberRepository.save(member);
        
    }
    @Override
    public JwtTokenDto signIn(String username, String password){
        UsernamePasswordAuthenticationToken authenticationToken = 
            new UsernamePasswordAuthenticationToken(username, password);
        
        Authentication authentication =
            authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        JwtTokenDto jwtToken = jwtTokenProvider.generateToken(authentication);
        return jwtToken;
    }
}
