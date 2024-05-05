package ticketing.ticket.membercoupon.repository;

import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;

import java.util.List;
import java.util.Optional;

public interface MemberCouponRepository {

    void save(MemberCoupon memberCoupon);

    Optional<MemberCoupon> findById(Long id);

    void delete(MemberCoupon memberCoupon);

}