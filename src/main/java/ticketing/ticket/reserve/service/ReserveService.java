package ticketing.ticket.reserve.service;
import java.util.*;

import ticketing.ticket.reserve.domain.dto.ReserveDto;
public interface ReserveService {
    void setReserve(Long memberId, Long seatId, Long performanceDetailId);
    List<ReserveDto> getReserveByPerformanceDetailId(Long performanceDetailId);
    
}