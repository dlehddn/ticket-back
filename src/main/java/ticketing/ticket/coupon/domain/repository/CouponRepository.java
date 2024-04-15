package ticketing.ticket.coupon.domain.repository;

import java.util.*;

import ticketing.ticket.coupon.domain.entity.Coupon;
public interface CouponRepository {
    void save(Coupon coupon);
    List<Coupon> findAll();
}
