package ticketing.ticket.membercoupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.membercoupon.domain.dto.CouponSaveDto;
import ticketing.ticket.membercoupon.domain.dto.MyCouponsDto;
import ticketing.ticket.membercoupon.service.MemberCouponService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-coupons")
public class MemberCouponController {

    private final MemberCouponService memberCouponService;

    @PostMapping
    public ResponseEntity<Void> saveCoupon(@RequestBody final CouponSaveDto saveDto) {
        memberCouponService.saveCoupon(saveDto.getMemberId(), saveDto.getCouponId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<MyCouponsDto>> getMyAllCoupons(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberCouponService.getMyCoupons(memberId));
    }

}
