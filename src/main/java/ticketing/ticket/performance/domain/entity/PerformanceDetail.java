package ticketing.ticket.performance.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

@Entity
public class PerformanceDetail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceDetailId;

    private Long performanceId;
    private String artist;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer price;
}
