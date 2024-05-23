package ticketing.ticket.member.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.common.aop.annotation.TimeTrace;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.domain.entity.QMember;

import java.util.Optional;

import static ticketing.ticket.member.domain.entity.QMember.member;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    @Override
    @TimeTrace
    public Optional<Member> findByUsername(String username) {
        return Optional.ofNullable(queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.roles).fetchJoin()
                .where(equalEmail(username))
                .fetchOne());
    }

    private BooleanExpression equalEmail(String email) {
        return member.email
                .eq(email);
    }
}
