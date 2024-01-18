package ticketing.ticket.reserve.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.reserve.domain.dto.SeatSaveDto;
import ticketing.ticket.reserve.domain.entity.Seat;
import ticketing.ticket.reserve.repository.SeatRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatService {
    private final SeatRepository seatRepository;

    public void saveSeat(SeatSaveDto seatSaveDto) {
        seatRepository.save(seatSaveDto);
    }

    public Seat findSeatById(Long id) {
        return seatRepository.findById(id);
    }

    public void removeSeat(Long id) {
        seatRepository.remove(id);
    }
}
