package ticketing.ticket.coupon.domain.repository.impl;

import java.util.List;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.domain.repository.CouponRepository;
@Repository
public class CouponRepositoryImpl implements CouponRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    public void save(Coupon coupon) {
        if (coupon.getCouponId() == null) {
            em.persist(coupon);
        } else {
            em.merge(coupon);
        }
    }

    @Override
    public List<Coupon> findAll() {
        return em.createQuery("select c from Coupon c", Coupon.class).getResultList();
    }
    
}
