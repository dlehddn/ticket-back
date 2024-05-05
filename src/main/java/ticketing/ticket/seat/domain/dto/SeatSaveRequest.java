package ticketing.ticket.seat.domain.dto;

import lombok.Getter;

@Getter
public class SeatSaveRequest {
    private Integer row;
    private Integer col;
}
