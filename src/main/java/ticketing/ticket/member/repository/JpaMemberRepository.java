package ticketing.ticket.member.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.entity.Member;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        String jpql = "select m from Member m where m.email = :email";
        Member member = em.createQuery(jpql, Member.class)
                .setParameter("email", username)
                .getSingleResult();
        return Optional.ofNullable(member);
    }
}
