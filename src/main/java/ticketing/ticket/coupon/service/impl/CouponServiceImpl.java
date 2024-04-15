package ticketing.ticket.coupon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ticketing.ticket.coupon.domain.dto.CouponDto;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.domain.repository.CouponRepository;
import ticketing.ticket.coupon.service.CouponService;
import java.util.*;
@Service
@Transactional
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository){
        this.couponRepository = couponRepository;
    }

    @Override
    public void setCoupon(CouponDto couponDto) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponDto.getCouponName());
        coupon.setEndDate(couponDto.getEndDate());
        coupon.setPercent(couponDto.getPercent());
        coupon.setQuantity(couponDto.getQuantity());
        couponRepository.save(coupon);
    }

    @Override
    public List<CouponDto> getAllCoupons() {
        List<Coupon> coupons = couponRepository.findAll();
        List<CouponDto> couponDtos = new ArrayList<>();
        coupons.forEach(c->{
            couponDtos.add(c.toDto());
        });
        return couponDtos;

   }
    
}
