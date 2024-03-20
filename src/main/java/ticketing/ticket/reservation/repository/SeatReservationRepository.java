package ticketing.ticket.reservation.repository;
import ticketing.ticket.reservation.domain.dto.BulkReservationDto;
import java.util.List;

public interface SeatReservationRepository {
    void bulkInsert(List<BulkReservationDto> reservations);

    void updateAvailable(Long reserveId);
}
