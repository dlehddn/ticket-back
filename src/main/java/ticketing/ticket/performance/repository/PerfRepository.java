package ticketing.ticket.performance.repository;

import ticketing.ticket.performance.domain.dto.PerfDto;
import ticketing.ticket.performance.domain.entity.Performance;

import java.util.List;

public interface PerfRepository {
    void save(PerfDto perfDto);
    Performance findById(Long id);
    List<Performance> findAll();
}
