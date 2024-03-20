package ticketing.ticket.seat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.seat.domain.dto.SeatSaveDto;
import ticketing.ticket.seat.domain.entity.Seat;
import ticketing.ticket.seat.repository.SeatRepository;

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
