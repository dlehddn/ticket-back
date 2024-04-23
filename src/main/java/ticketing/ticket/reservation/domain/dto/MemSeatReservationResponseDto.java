package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

@Getter
@Setter
@Builder
public class MemSeatReservationResponseDto {
    private SeatReservation seatReservation;
    private Integer totalPrice;
}
