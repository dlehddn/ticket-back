package ticketing.ticket.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.repository.PerfDetailRepository;
import ticketing.ticket.reservation.domain.dto.BulkReservationDto;
import ticketing.ticket.reservation.repository.SeatReservationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfDetailService {

    private final PerfDetailRepository perfDetailRepository;
    private final SeatReservationRepository seatReservationRepository;

    public void savePerfDetail(PerfDetailSaveDto dto, Long perfId) {
        Long perfDetailId = perfDetailRepository.save(dto, perfId);

        List<BulkReservationDto> reservations = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            reservations.add(BulkReservationDto.builder()
                    .performanceDetailId(perfDetailId)
                    .seatId((long) i)
                    .build());
        }
        seatReservationRepository.bulkInsert(reservations);
    }

    public PerfDetailResponseDto findById(Long id) {
        return perfDetailRepository.findById(id);
    }

    public List<PerfDetailResponseDto> findAllByPerf(Long perfId) {
        return perfDetailRepository.findAllByPerf(perfId);
    }
}
