package ticketing.ticket.membercoupon.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.coupon.repository.CouponRepository;
import ticketing.ticket.coupon.service.CouponService;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberCouponServiceTest {

    @Autowired
    MemberCouponService memberCouponService;

    @Autowired
    CouponRepository couponRepository;

    @Test
    @DisplayName("동시 쿠폰 발급 상황")
    void ThreadSafeTest() throws InterruptedException {
        //given
        Coupon coupon1 = couponRepository.findById(9L).orElseThrow();

        ExecutorService executorService = Executors.newFixedThreadPool(30);
        CountDownLatch countDownLatch = new CountDownLatch(30);

        //when
        for (int i = 0; i < 30; i++) {
            executorService.execute(() -> {
                memberCouponService.saveCoupon(3L, 9L);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();

        Coupon coupon2 = couponRepository.findById(9L).orElseThrow();
        //then
        Assertions.assertThat(coupon2.getQuantity()).isEqualTo(coupon1.getQuantity() - 30);
    }
}