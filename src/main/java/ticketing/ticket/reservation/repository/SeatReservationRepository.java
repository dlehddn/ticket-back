package ticketing.ticket.reservation.repository;
import ticketing.ticket.reservation.domain.dto.BulkReservationDto;
import ticketing.ticket.reservation.domain.dto.SeatReservationResponseDto;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

import java.util.List;
import java.util.Optional;

public interface SeatReservationRepository {
    void bulkInsert(List<BulkReservationDto> reservations);

    void updateAvailable(Long reserveId);

    List<SeatReservationResponseDto> findAllByPerfDetailId(Long perfDetailId);

    Optional<SeatReservation> findById(Long id);
}
