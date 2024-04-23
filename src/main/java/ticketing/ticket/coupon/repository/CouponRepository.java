package ticketing.ticket.coupon.repository;

import ticketing.ticket.coupon.domain.entity.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository {

    void save(Coupon coupon);

    List<Coupon> findAll();

    Optional<Coupon> findById(Long couponId);
    void update(Long couponId);
}
