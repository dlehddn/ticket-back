package ticketing.ticket.membercoupon.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
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
         * 체크 제약 위배 시 JpaSystemException 발생 (쿠폰 수량 >= 0)
         * 별도 설정이 없다면 서비스 단의 메서드 호출 후 트랜잭션이 커밋될 때 예외 발생
         * -> try/catch 로 Exception 을 잡지 못함
         * update() 메서드에서 flush()를 호출
         */
        try {
            couponRepository.update(couponId);
        } catch (JpaSystemException e) {
            throw new InsufficientCouponException(e, SQLErrorCode.INSUFFICIENT_COUPON);
        }

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
