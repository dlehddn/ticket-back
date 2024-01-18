package ticketing.ticket.reserve.domain.dto;

import lombok.Data;

@Data
public class ReserveResponseDto {

    Long memberId;
    String memberName;

    String SeatName;

    Long perfDetailId;
    String artist;

    public ReserveResponseDto(Long memberId, String memberName, String seatName, Long perfDetailId, String artist) {
        this.memberId = memberId;
        this.memberName = memberName;
        SeatName = seatName;
        this.perfDetailId = perfDetailId;
        this.artist = artist;
    }
}
