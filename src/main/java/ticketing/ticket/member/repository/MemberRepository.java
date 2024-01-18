package ticketing.ticket.member.repository;

import ticketing.ticket.member.domain.dto.MemberDto;

public interface MemberRepository {
    void save(MemberDto memberDto);
}
