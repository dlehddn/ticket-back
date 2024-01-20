package ticketing.ticket.reserve.domain.dto;

import lombok.Data;

@Data
public class ReserveSaveDto {

    Long memberId;
    Long seatId;
    Long perfDetailId;
    Integer totalPrice;

    public ReserveSaveDto(Long memberId, Long seatId, Long perfDetailId) {
        this.memberId = memberId;
        this.seatId = seatId;
        this.perfDetailId = perfDetailId;
    }
}
