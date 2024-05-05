package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MemSeatReservationDto {
    private Long seatReservationId;
    private Long memberCouponId;
    private Long memberId;
    private Integer totalPrice;
}
