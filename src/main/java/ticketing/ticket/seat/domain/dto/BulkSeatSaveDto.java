package ticketing.ticket.seat.domain.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
public class BulkSeatSaveDto {
    private String name;
    private String grade;

    @Builder
    public BulkSeatSaveDto(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
}
