package ticketing.ticket.membercoupon.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.common.error.SQLErrorCode;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.repository.CouponRepository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.membercoupon.domain.dto.MyCouponsDto;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;
import ticketing.ticket.membercoupon.exception.DuplicatedCouponException;
import ticketing.ticket.membercoupon.repository.MemberCouponRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional()
public class MemberCouponService {

    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MemberCouponRepository memberCouponRepository;

    public void saveCoupon(Long memberId, Long couponId) {
        /**
         * check 제약조건 예외 가능
         * -> 트랜잭션이 커밋될 때 발생하므로 controller 에서 예외처리를 해야함.
         * -> 예외 누수 문제
         * -> check 제약 조건은 해당 테이블에서 유일하므로 직접 errorCode + sqlState 로 catch
         * SQL Error: 3819, SQLState: HY000
         */
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
