package ticketing.ticket.membercoupon.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;

@Repository
@RequiredArgsConstructor
public class JpaMemberCouponRepository implements MemberCouponRepository{

    private final EntityManager em;

    @Override
    public void save(MemberCoupon memberCoupon) {
        em.persist(memberCoupon);
    }
}
