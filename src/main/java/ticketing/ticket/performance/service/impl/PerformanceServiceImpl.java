package ticketing.ticket.performance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketing.ticket.performance.domain.dto.PerformanceDetailDto;
import ticketing.ticket.performance.domain.dto.PerformanceDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.repository.PerformanceRepository;

import ticketing.ticket.performance.service.PerformanceService;
@Service
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;

    @Autowired
    public PerformanceServiceImpl(PerformanceRepository performanceRepository){
        this.performanceRepository = performanceRepository;
    }
    // 공연 카테고리 저장
    @Override
    public void setPerformance(PerformanceDto PerformanceDto) {
       
        Performance performance = new Performance();
        performance.setName(PerformanceDto.getName());
        performanceRepository.save(performance);
    }
    // 단일 공연 카테고리 조회
    @Override
    public PerformanceDto getPerformance(Long PerformanceId) {
       Performance performance= performanceRepository.findById(PerformanceId);
       PerformanceDto performanceDto = new PerformanceDto();
       performanceDto.setPerformanceId(performance.getPerformanceId());
       performanceDto.setName(performance.getName());
       return performanceDto;
    }
    // 모든 공연 카테고리 조회
    @Override
    public List<PerformanceDto> getAllPerformance() {
        List<Performance> performanceList = performanceRepository.findAllWithDetail();
        List<PerformanceDto> dtoList = new ArrayList<>();
        List<PerformanceDetailDto> detailList = new ArrayList<>();

        performanceList.forEach(p -> {
            PerformanceDto performanceDto = new PerformanceDto();
            if (!p.getPerformanceDetailList().isEmpty()) {
                p.getPerformanceDetailList().forEach(pd->{
                    PerformanceDetailDto performanceDetailDto = new PerformanceDetailDto();
                    performanceDetailDto.setPerformanceDetailId(pd.getPerformanceDetailId());
                    performanceDetailDto.setArtist(pd.getArtist());
                    performanceDetailDto.setStartTime(pd.getStartTime());
                    performanceDetailDto.setEndTime(pd.getEndTime());
                    performanceDetailDto.setPrice(pd.getPrice());
                    performanceDetailDto.setPerformanceId(pd.getPerformance().getPerformanceId());
                    detailList.add(performanceDetailDto);
                });
                performanceDto.setPerformanceDetailDtoList(detailList);
            }
           
            performanceDto.setName(p.getName());
            performanceDto.setPerformanceId(p.getPerformanceId());
            

        
            dtoList.add(performanceDto);
        });

        
        return dtoList;
    }
    // 공연 카테고리 삭제
    @Override
    public void deletePerformance(Long PerformanceId) {
        performanceRepository.deleteById(PerformanceId);
    }
    
    


}
