package ticketing.ticket.coupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.coupon.domain.dto.CouponResponseDto;
import ticketing.ticket.coupon.domain.dto.CouponSaveDto;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.repository.CouponRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    public void createCoupon(CouponSaveDto couponSaveDto) {
        Coupon coupon = Coupon.builder()
                .name(couponSaveDto.getName())
                .percent(couponSaveDto.getPercent())
                .quantity(couponSaveDto.getQuantity())
                .endDate(couponSaveDto.getEndDate())
                .build();
        couponRepository.save(coupon);
    }

    public List<CouponResponseDto> getAllCoupon() {
        return couponRepository.findAll()
                .stream()
                .map(c -> CouponResponseDto.builder()
                        .couponId(c.getCouponId())
                        .name(c.getName())
                        .percent(c.getPercent())
                        .quantity(c.getQuantity())
                        .endDate(c.getEndDate())
                        .build())
                .collect(Collectors.toList());
    }
}
