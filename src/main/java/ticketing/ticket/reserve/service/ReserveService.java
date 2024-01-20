package ticketing.ticket.reserve.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.repository.PerfDetailRepository;
import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;
import ticketing.ticket.reserve.domain.dto.ReserveSaveDto;
import ticketing.ticket.reserve.domain.entity.Seat;
import ticketing.ticket.reserve.repository.ReserveRepository;
import ticketing.ticket.reserve.repository.SeatRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final SeatRepository seatRepository;
    private final PerfDetailRepository perfDetailRepository;

    public void saveReserve(ReserveSaveDto saveDto) {
        // 아직도 예약 안됐는지 체크하는 로직
        List<ReserveResponseDto> findDto = reserveRepository.findAllByDetail(saveDto.getPerfDetailId());
        if(checkAvailable(findDto, saveDto.getSeatId())) {
            // 퍼포먼스 디테일 꺼내오고 좌석 꺼내오기 -> totalPrice 계산
            // 이후 repository.save() 요청
            int totalPrice = 0;
            PerfDetailResponseDto perfDetail = perfDetailRepository.findById(saveDto.getPerfDetailId());
            Seat seat = seatRepository.findById(saveDto.getSeatId());
            if (seat.getGrade().equals("VIP")) {
                totalPrice = perfDetail.getPrice() * 120 / 100;
            } else if (seat.getGrade().equals("Normal")) {
                totalPrice = perfDetail.getPrice();
            }
            saveDto.setTotalPrice(totalPrice);
            reserveRepository.save(saveDto);
            log.info("좌석 예약 성공");
        } else {
            log.info("예약할 수 없는 좌석입니다.");
        }
    }

    private static boolean checkAvailable(List<ReserveResponseDto> dtoList, Long seatId) {
        if (dtoList == null) {
            return true;
        }
        if (dtoList.size() == 0)
            return true;
        return !dtoList.stream()
                .anyMatch(dto -> dto.getSeatId() == seatId);
    }

    public List<ReserveResponseDto> findAllByPerfDetail(Long perfId) {
        return reserveRepository.findAllByDetail(perfId);
    }
}
