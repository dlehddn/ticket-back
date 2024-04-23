package ticketing.ticket.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.common.jwt.JwtTokenProvider;
import ticketing.ticket.member.domain.dto.JwtTokenDto;
import ticketing.ticket.member.domain.dto.LogInMemberDto;
import ticketing.ticket.member.domain.dto.MemberResponse;
import ticketing.ticket.member.domain.dto.SignUpDto;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public void saveMember(SignUpDto signUpDto) {
        Member member = Member.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        member.getRoles().add("USER");
        memberRepository.save(member);
    }

    public ResponseEntity<MemberResponse> signIn(String username, String password) {

        LogInMemberDto userDetails = (LogInMemberDto) customUserDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password);

        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        JwtTokenDto jwtToken = jwtTokenProvider.generateToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwtToken.getAccessToken());

        Member member = ((LogInMemberDto) authentication.getPrincipal()).getMember();
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(MemberResponse.builder()
                        .memberId(member.getMemberId())
                        .name(member.getName())
                        .email(member.getEmail())
                        .build());
    }
}
