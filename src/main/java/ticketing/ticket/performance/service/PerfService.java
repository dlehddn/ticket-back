package ticketing.ticket.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.performance.domain.dto.PerfDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.repository.PerfRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfService {

    private final PerfRepository perfRepository;

    public void savePerformance(PerfDto perfDto) {
        perfRepository.save(perfDto);
    }

    public Performance findById(Long id) {
        return perfRepository.findById(id);
    }

    public List<Performance> findAll() {
        return perfRepository.findAll();
    }
}
