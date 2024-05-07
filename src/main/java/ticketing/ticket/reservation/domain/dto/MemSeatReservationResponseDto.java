package ticketing.ticket.reservation.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemSeatReservationResponseDto {
    private String category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String artist;
    private String seatName;
    private String seatGrade;
    private Integer totalPrice;
}
