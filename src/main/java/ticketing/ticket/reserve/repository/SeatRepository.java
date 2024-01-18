package ticketing.ticket.reserve.repository;

import ticketing.ticket.reserve.domain.dto.SeatSaveDto;
import ticketing.ticket.reserve.domain.entity.Seat;

public interface SeatRepository {
    void save(SeatSaveDto seatSaveDto);

    Seat findById(Long id);

    void remove(Long id);
}
