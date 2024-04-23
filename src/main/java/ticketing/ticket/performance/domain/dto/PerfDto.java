package ticketing.ticket.performance.domain.dto;

import lombok.Data;

@Data
public class PerfDto {
    private String name;

    public PerfDto(String name) {
        this.name = name;
    }

    public PerfDto() {
    }
}
