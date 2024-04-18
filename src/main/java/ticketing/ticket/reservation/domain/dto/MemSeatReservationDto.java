package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemSeatReservationDto {
    private Long seatReservationId;
    private Long memberId;
    private Integer totalPrice;
}
