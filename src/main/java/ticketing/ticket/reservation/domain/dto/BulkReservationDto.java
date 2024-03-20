package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BulkReservationDto {
    private Long performanceDetailId;
    private Long seatId;
}
