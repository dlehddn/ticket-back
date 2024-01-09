package ticketing.ticket.reserve.domain.dto;

import lombok.Data;

@Data
public class SeatDto {
    
    private Long seatId;

    
    private String name;

   
    private String grade;

    
    private Integer price;
}
