package ticketing.ticket.membercoupon.domain.dto;


import lombok.Builder;
import lombok.Getter;
import ticketing.ticket.coupon.domain.entity.Coupon;

@Getter
public class MyCouponsDto {
    private Coupon coupon;
    private Long memberCouponId;

    @Builder
    public MyCouponsDto(Coupon coupon, Long memberCouponId) {
        this.coupon = coupon;
        this.memberCouponId = memberCouponId;
    }
}
