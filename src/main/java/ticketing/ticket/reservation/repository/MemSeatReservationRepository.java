package ticketing.ticket.reservation.repository;

import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;

public interface MemSeatReservationRepository {

    void save(MemSeatReservationDto reservationDto);
}
