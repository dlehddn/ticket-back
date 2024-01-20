package ticketing.ticket.reserve.service;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.reserve.domain.dto.ReserveSaveDto;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Transactional
class ReserveServiceTest {

    @Autowired
    private ReserveService reserveService;

    @Test
    @DisplayName("동시 좌석 예약 상황")
    void ThreadSafeTest() throws InterruptedException {
        //given
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ReserveSaveDto reserveSaveDto = new ReserveSaveDto(2L, 15L, 1L);
        reserveSaveDto.setTotalPrice(1000);

        //when
        for (int i = 0; i < 5; i++) {
            executorService.execute(() -> {
                reserveService.saveReserve(reserveSaveDto);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();

        //then
    }


}