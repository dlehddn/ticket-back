package ticketing.ticket.membercoupon.repository;

import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;

import java.util.List;

public interface MemberCouponRepository {

    void save(MemberCoupon memberCoupon);

}
