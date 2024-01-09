package ticketing.ticket.reserve.repository;

import ticketing.ticket.reserve.domain.entity.Reserve;
import java.util.*;

public interface ReserveRepository {
    void save(Reserve reserve);
    List<Reserve> findByPerformanceDetailId(Long performanceDetailId);
    
}
