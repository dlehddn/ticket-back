package ticketing.ticket.coupon.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;
import ticketing.ticket.coupon.domain.entity.Coupon;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCouponRepository implements CouponRepository{

    private final EntityManager em;

    @Override
    public void save(Coupon coupon) {
        em.persist(coupon);
    }

    @Override
    public List<Coupon> findAll() {
        String jpql = "select c from Coupon c";
        return em.createQuery(jpql, Coupon.class)
                .getResultList();
    }

    @Override
    public Optional<Coupon> findById(Long couponId) {
        return Optional.ofNullable(em.find(Coupon.class, couponId));
    }

    @Override
    public void update(Long couponId) {
        Coupon coupon = em.find(Coupon.class, couponId, LockModeType.PESSIMISTIC_WRITE);
        coupon.setQuantity(coupon.getQuantity() - 1);
//        em.flush();
    }
}
