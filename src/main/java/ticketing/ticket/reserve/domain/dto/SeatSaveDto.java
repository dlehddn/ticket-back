package ticketing.ticket.reserve.domain.dto;

import lombok.Data;

@Data
public class SeatSaveDto {
    private String name;
    private String grade;

    public SeatSaveDto(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
