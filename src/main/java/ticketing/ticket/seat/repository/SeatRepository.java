package ticketing.ticket.seat.repository;

import ticketing.ticket.seat.domain.dto.BulkSeatSaveDto;
import ticketing.ticket.seat.domain.entity.Seat;

import java.util.List;

public interface SeatRepository {
    void save(List<BulkSeatSaveDto> seats);

    Seat findById(Long id);

    void remove(Long id);
}
