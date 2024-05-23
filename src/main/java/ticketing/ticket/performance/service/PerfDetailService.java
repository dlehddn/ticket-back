package ticketing.ticket.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.domain.dto.PerfSearchDto;
import ticketing.ticket.performance.repository.PerfDetailRepository;
import ticketing.ticket.reservation.domain.dto.BulkReservationDto;
import ticketing.ticket.reservation.repository.SeatReservationRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfDetailService {

    private final PerfDetailRepository perfDetailRepository;
    private final SeatReservationRepository seatReservationRepository;

    public void savePerfDetail(PerfDetailSaveDto dto, Long perfId) {
        for (int i = 0; i < 10000; i++) {
            perfDetailRepository.save(dto, 1L);
            perfDetailRepository.save(dto, 2L);
            perfDetailRepository.save(dto, 3L);
            perfDetailRepository.save(dto, 4L);
            perfDetailRepository.save(dto, 5L);
            perfDetailRepository.save(dto, 6L);
            perfDetailRepository.save(dto, 7L);
        }

//        List<BulkReservationDto> reservations = new ArrayList<>();
//        for (int i = 1; i <= 70; i++) {
//            reservations.add(BulkReservationDto.builder()
//                    .performanceDetailId(perfDetailId)
//                    .seatId((long) i)
//                    .build());
//        }
//        seatReservationRepository.bulkInsert(reservations);
    }

    public PerfDetailResponseDto findById(Long id) {
        return perfDetailRepository.findById(id);
    }

    public List<PerfDetailResponseDto> findAllByPerf(PerfSearchDto perfSearchDto) {
        return perfDetailRepository.findAllByPerf(perfSearchDto)
                .stream()
                .map(p -> new PerfDetailResponseDto(p))
                .collect(Collectors.toList());
    }
}
