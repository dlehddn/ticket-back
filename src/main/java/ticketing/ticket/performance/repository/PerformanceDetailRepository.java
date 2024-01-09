package ticketing.ticket.performance.repository;


import java.util.List;

import ticketing.ticket.performance.domain.entity.PerformanceDetail;

public interface PerformanceDetailRepository {
    void save(PerformanceDetail performanceDetail);

    PerformanceDetail findById(Long performanceDetailId);
    
    List<PerformanceDetail> findAll();

    //카테고리별 조회
    List<PerformanceDetail> findByPerformanceId(Long PerformanceId);


    void deleteById(Long performanceDetailId);
}
