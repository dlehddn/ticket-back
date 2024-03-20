package ticketing.ticket.seat.repository;

import ticketing.ticket.seat.domain.dto.SeatSaveDto;
import ticketing.ticket.seat.domain.entity.Seat;

public interface SeatRepository {
    void save(SeatSaveDto seatSaveDto);

    Seat findById(Long id);

    void remove(Long id);
}
