package ticketing.ticket.reservation.service;

import ticketing.ticket.reservation.domain.dto.ReservationRequestDto;

public interface ReservationService {
    void setReservation(ReservationRequestDto reservationRequestDto);
}
