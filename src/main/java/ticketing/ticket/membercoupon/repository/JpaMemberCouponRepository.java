package ticketing.ticket.membercoupon.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaMemberCouponRepository implements MemberCouponRepository{

    private final EntityManager em;

    @Override
    public void save(MemberCoupon memberCoupon) {
        em.persist(memberCoupon);
    }

    @Override
    public Optional<MemberCoupon> findById(Long id) {
        return Optional.ofNullable(em.find(MemberCoupon.class, id));
    }

    @Override
    public void delete(MemberCoupon memberCoupon) {
        em.remove(memberCoupon);
    }
}
