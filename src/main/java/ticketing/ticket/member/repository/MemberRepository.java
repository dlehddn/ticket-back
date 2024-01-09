package ticketing.ticket.member.repository;

import ticketing.ticket.member.domain.entity.Member;

public interface MemberRepository {
    Member findById(Long memberId);
}
