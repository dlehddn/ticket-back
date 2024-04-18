package ticketing.ticket.membercoupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.repository.CouponRepository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.membercoupon.domain.dto.MyCouponsDto;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;
import ticketing.ticket.membercoupon.repository.MemberCouponRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;

    public void saveCoupon(Long memberId, Long couponId) {
        // 제약 조건 예외 가능 -> 0개 밑으로 떨어질 수 없다
        couponRepository.update(couponId);

        // NoSuchElementException 가능
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow();

        // 제약 조건 예외 가능 -> 중복 쿠폰 저장 상황
        memberCouponRepository.save(MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build());
    }

    public List<MyCouponsDto> getMyCoupons(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));

        return member.getMemberCoupons()
                .stream()
                .map(mc -> MyCouponsDto.builder()
                        .coupon(mc.getCoupon())
                        .build())
                .collect(Collectors.toList());

    }


    // 쿠폰 사용했을 때 삭제하는 로직 만들어야함
}
