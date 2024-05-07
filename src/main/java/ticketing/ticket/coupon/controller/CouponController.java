package ticketing.ticket.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ticketing.ticket.coupon.domain.dto.CouponDto;
import ticketing.ticket.coupon.service.CouponService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;



@RestController
@RequestMapping("/coupon")
public class CouponController {
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService){
        this.couponService = couponService;
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/save")
    public void postMethodName(@RequestBody CouponDto couponDto) {
      
        couponService.setCoupon(couponDto);       
        
    }
    
    @GetMapping("/all")
    public List<CouponDto> getAllCouponDtos () {
        return couponService.getAllCoupons();
    }
    

}