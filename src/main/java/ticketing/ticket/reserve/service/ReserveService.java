package ticketing.ticket.reserve.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;
import ticketing.ticket.reserve.repository.ReserveRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReserveService {

    private final ReserveRepository reserveRepository;

    public void saveReserve(Long memberId, Long seatId, Long perfDetailId) {
        reserveRepository.save(memberId, seatId, perfDetailId);
    }

    public List<ReserveResponseDto> findAllByPerfDetail(Long perfId) {
        return reserveRepository.findAllByDetail(perfId);
    }
}
