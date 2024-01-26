package ticketing.ticket.reserve.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import ticketing.ticket.reserve.controller.ReserveController;
import ticketing.ticket.reserve.domain.dto.ReserveSaveDto;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@SpringBootTest
class ReserveServiceTest {

    @Autowired
    private ReserveService reserveService;

    @Test
    @DisplayName("동시 좌석 예약 상황")
    void ThreadSafeTest() throws InterruptedException {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ReserveSaveDto reserveSaveDto = new ReserveSaveDto(2L, 3L, 1L);
        reserveSaveDto.setTotalPrice(1000);
        AtomicBoolean isDuplicated = new AtomicBoolean(false);
        //when
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                try {
                    reserveService.saveReserve(reserveSaveDto);
                } catch (DataIntegrityViolationException e) {
                    isDuplicated.set(true);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        //then
        Assertions.assertThat(isDuplicated).isFalse();
    }
}