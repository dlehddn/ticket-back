package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

@Getter
@Setter
@Builder
public class MemSeatReservationDto {
    private Long seatReservationId;
    private Long memberId;
    private Integer totalPrice;
}
