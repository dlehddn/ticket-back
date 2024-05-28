package ticketing.ticket.membercoupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.common.error.errorcodes.SQLErrorCode;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.repository.CouponRepository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.membercoupon.domain.dto.MyCouponsDto;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;
import ticketing.ticket.membercoupon.exception.DuplicatedCouponException;
import ticketing.ticket.membercoupon.exception.InsufficientCouponException;
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
        /**
         * 쿠폰 수량 예외 더블 체크
         * 1. 쿠폰 수량 확인 후, 0 이하라면 커스텀 예외 던짐
         * 2. 발생하지 않을 상황이겠지만, 중요한 비지니스 로직이므로 DB 체크 제약 조건을 추가
         *     -> JpaSystemException root cause SQLException 가능
         *     -> 체크 제약조건을 유일하므로 ControllerAdvice 에서 직접 처리
         *     -> SQL Error: 3819, SQLState: HY000
         */
        Coupon requestedCoupon = couponRepository.findById(couponId).orElseThrow();
        if (requestedCoupon.getQuantity() <= 0) {
            throw new InsufficientCouponException(SQLErrorCode.INSUFFICIENT_COUPON);
        }
        couponRepository.update(couponId);

        // NoSuchElementException 가능
        Member member = memberRepository.findById(memberId)
                .orElseThrow();
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow();

        // 제약 조건 예외 가능 -> 중복 쿠폰 저장 상황
        try {
            memberCouponRepository.save(MemberCoupon.builder()
                    .member(member)
                    .coupon(coupon)
                    .build());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedCouponException(e, SQLErrorCode.DUPLICATED_COUPON);
        }
    }

    public List<MyCouponsDto> getMyCoupons(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 회원을 찾을 수 없습니다."));

        return member.getMemberCoupons()
                .stream()
                .map(mc -> MyCouponsDto.builder()
                        .coupon(mc.getCoupon())
                        .memberCouponId(mc.getMemberCouponId())
                        .build())
                .collect(Collectors.toList());

    }
}
