package ticketing.ticket.performance.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PerformanceDetailDto {
    private String type;
    private String artist;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer price;

    public PerformanceDetailDto(String type, String artist, LocalDateTime startTime, LocalDateTime endTime, Integer price) {
        this.type = type;
        this.artist = artist;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }
}
