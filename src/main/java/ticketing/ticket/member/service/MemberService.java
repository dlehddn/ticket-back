package ticketing.ticket.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.member.domain.dto.MemberDto;
import ticketing.ticket.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public void saveMember(MemberDto memberDto) {
        memberRepository.save(memberDto);
    }
}
