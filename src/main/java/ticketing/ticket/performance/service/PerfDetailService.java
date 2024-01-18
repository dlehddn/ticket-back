package ticketing.ticket.performance.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.repository.PerfDetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PerfDetailService {

    private final PerfDetailRepository perfDetailRepository;

    public void savePerfDetail(PerfDetailSaveDto dto, Long perfId) {
        perfDetailRepository.save(dto, perfId);
    }

    public PerfDetailResponseDto findById(Long id) {
        return perfDetailRepository.findById(id);
    }

    public List<PerfDetailResponseDto> findAll() {
        return perfDetailRepository.findAll();
    }
}
