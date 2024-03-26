package ticketing.ticket.membercoupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.membercoupon.service.MemberCouponService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-coupon")
public class MemberCouponController {

    private final MemberCouponService memberCouponService;

    @GetMapping("/save/{memberId}/{couponId}")
    public void saveCoupon(@PathVariable Long memberId, @PathVariable Long couponId) {
        memberCouponService.saveCoupon(memberId, couponId);
    }
}
