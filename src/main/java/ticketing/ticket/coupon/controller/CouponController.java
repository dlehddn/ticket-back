package ticketing.ticket.coupon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.coupon.domain.dto.CouponResponseDto;
import ticketing.ticket.coupon.domain.dto.CouponSaveDto;
import ticketing.ticket.coupon.service.CouponService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/save")
    public void createCoupon(@RequestBody CouponSaveDto couponSaveDto) {
        couponService.createCoupon(couponSaveDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CouponResponseDto>> getAllCoupon() {
        List<CouponResponseDto> allCoupon = couponService.getAllCoupon();
        return ResponseEntity.ok(allCoupon);
    }

}
