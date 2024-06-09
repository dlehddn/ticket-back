package ticketing.ticket.coupon.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.domain.entity.QCoupon;

import java.util.List;
import java.util.Optional;

import static ticketing.ticket.coupon.domain.entity.QCoupon.coupon;

@Repository
@RequiredArgsConstructor
public class JpaCouponRepository implements CouponRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(Coupon coupon) {
        em.persist(coupon);
    }

    @Override
    public List<Coupon> findAll() {
        return queryFactory
                .select(coupon)
                .from(coupon)
                .fetch();
    }

    @Override
    @Transactional
    public Optional<Coupon> findById(Long couponId) {
        return Optional.ofNullable(em.find(Coupon.class, couponId));
    }

    @Override
    public void update(Long couponId) {
        Coupon coupon = em.find(Coupon.class, couponId, LockModeType.PESSIMISTIC_WRITE);
        coupon.setQuantity(coupon.getQuantity() - 1);
        em.flush();
    }
}
