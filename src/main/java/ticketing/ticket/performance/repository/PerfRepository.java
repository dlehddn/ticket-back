package ticketing.ticket.performance.repository;

import ticketing.ticket.performance.domain.dto.PerformanceDto;
import ticketing.ticket.performance.domain.entity.Performance;

import java.util.List;

public interface PerformanceRepository {
    void save(PerformanceDto performanceDto);

    Performance findById(Long id);
    List<Performance> findAll();
}
