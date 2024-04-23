package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ticketing.ticket.seat.domain.entity.Seat;

@Getter
@Setter
@Builder
public class SeatReservationResponseDto {
    private Long seatReservationId;
    private Seat seat;
    private boolean available;
}
