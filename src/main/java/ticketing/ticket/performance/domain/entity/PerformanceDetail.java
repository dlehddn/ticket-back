package ticketing.ticket.performance.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import ticketing.ticket.common.entity.BaseEntity;

import java.time.LocalDateTime;

@Entity
@Data
public class PerformanceDetail extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceDetailId;

    @ManyToOne
    @JoinColumn(name = "performance_id")
    private Performance performance;

    private String artist;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer price;

    @Builder
    public PerformanceDetail(Performance performance, String artist, LocalDateTime startTime, LocalDateTime endTime, Integer price) {
        this.performance = performance;
        this.artist = artist;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    public PerformanceDetail() {
    }
}
