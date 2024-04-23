package ticketing.ticket.seat.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatSaveRequest {
    private Integer row;
    private Integer col;
}
