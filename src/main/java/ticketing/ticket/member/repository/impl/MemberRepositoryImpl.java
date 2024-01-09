package ticketing.ticket.member.repository.impl;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;
@Repository
public class MemberRepositoryImpl  implements MemberRepository{
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Member findById(Long memberId) {
      return em.find(Member.class, memberId);
    }
    
}
