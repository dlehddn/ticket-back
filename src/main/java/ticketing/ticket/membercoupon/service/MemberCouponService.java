package ticketing.ticket.membercoupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.repository.CouponRepository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;
import ticketing.ticket.membercoupon.repository.MemberCouponRepository;

@Service
@RequiredArgsConstructor
public class MemberCouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;

    @Transactional
    public void saveCoupon(Long memberId, Long couponId) {
        // 제약 조건 예외 가능
        couponRepository.update(couponId);

        // NoSuchElementException 가능
        // 다른 커스텀 예외로 던지자
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow();
        memberCouponRepository.save(MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build());
    }

    // 쿠폰 사용했을 때 삭제하는 로직 만들어야함
}
