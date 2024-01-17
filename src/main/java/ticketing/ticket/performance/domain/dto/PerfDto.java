package ticketing.ticket.performance.domain.dto;

import lombok.Data;

@Data
public class PerformanceDto {
    private String name;

    public PerformanceDto(String name) {
        this.name = name;
    }

    public PerformanceDto() {
    }
}
