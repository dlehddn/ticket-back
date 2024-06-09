package ticketing.ticket.reservation.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest
class MemSeatReservationServiceTest {
    @Autowired
    private MemSeatReservationService reserveService;

    @Test
    @DisplayName("동시 좌석 예약 상황")
    void ThreadSafeTest() throws InterruptedException {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        MemSeatReservationDto reservationDto = MemSeatReservationDto.builder()
                .seatReservationId(106L)
                .memberId(3L)
                .totalPrice(11000)
                .build();
        AtomicBoolean isDuplicated = new AtomicBoolean(false);
        //when
        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                try {
                    reserveService.reserveTicket(reservationDto);
                } catch (ObjectOptimisticLockingFailureException e) {
                    isDuplicated.set(true);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        //then
        Assertions.assertThat(isDuplicated).isTrue();
    }
}