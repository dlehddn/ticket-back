package ticketing.ticket.reserve.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReserveResponseDto {

    private Long reserveId;
    private Long memberId;
    private Long perfDetailId;
    private Long seatId;

    private String memberName;
    private String seatName;
    private String artist;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Integer totalPrice;

}
