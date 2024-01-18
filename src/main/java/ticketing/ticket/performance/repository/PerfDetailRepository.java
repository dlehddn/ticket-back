package ticketing.ticket.performance.repository;

import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import java.util.List;

public interface PerfDetailRepository {

    void save(PerfDetailSaveDto perfDetailSaveDto, Long perfId);

    PerfDetailResponseDto findById(Long perfDetailId);

    List<PerfDetailResponseDto> findAll();
}
