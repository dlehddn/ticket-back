package ticketing.ticket.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.dto.MemberDto;
import ticketing.ticket.member.domain.entity.Member;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Override
    public void save(MemberDto memberDto) {
        Member member = new Member(memberDto.getName(), memberDto.getEmail(), memberDto.getPassword());
        em.persist(member);
    }
}
