package ticketing.ticket.member.repository;

import ticketing.ticket.member.domain.entity.Member;

import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> findById(Long memberId);

    Optional<Member> findByUsername(String username);

}
