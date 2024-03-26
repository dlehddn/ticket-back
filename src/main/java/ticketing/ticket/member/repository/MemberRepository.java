package ticketing.ticket.member.repository;

import ticketing.ticket.member.domain.dto.MemberDto;
import ticketing.ticket.member.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(MemberDto memberDto);

    Optional<Member> findById(Long memberId);
}
