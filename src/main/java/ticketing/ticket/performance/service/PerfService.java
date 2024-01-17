package ticketing.ticket.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.performance.domain.dto.PerformanceDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.repository.PerformanceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerformanceService {

    private final PerformanceRepository performanceRepository;

    public void savePerformance(PerformanceDto performanceDto) {
        performanceRepository.save(performanceDto);
    }

    public Performance findById(Long id) {
        return performanceRepository.findById(id);
    }

    public List<Performance> findAll() {
        return performanceRepository.findAll();
    }
}
