package ticketing.ticket.performance.repository;

import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.domain.dto.PerfSearchDto;

import java.util.List;

public interface PerfDetailRepository {

    Long save(PerfDetailSaveDto perfDetailSaveDto, Long perfId);

    PerfDetailResponseDto findById(Long perfDetailId);

    List<PerfDetailResponseDto> findAllByPerf(PerfSearchDto perfSearchDto);
}
