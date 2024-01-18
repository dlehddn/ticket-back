package ticketing.ticket.performance.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;

import java.time.LocalDateTime;

@Data
public class PerfDetailResponseDto {
    private String type;
    private String artist;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime endTime;

    private Integer price;

    public PerfDetailResponseDto(String type, String artist, LocalDateTime startTime, LocalDateTime endTime, Integer price) {
        this.type = type;
        this.artist = artist;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public PerfDetailResponseDto(PerformanceDetail perfDetail) {
        this.type = perfDetail.getPerformance().getName();
        this.artist = perfDetail.getArtist();
        this.startTime = perfDetail.getStartTime();
        this.endTime = perfDetail.getEndTime();
        this.price = perfDetail.getPrice();
    }
}
