package ticketing.ticket.performance.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketing.ticket.performance.domain.dto.PerformanceDetailDto;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;
import ticketing.ticket.performance.repository.PerformanceDetailRepository;
import ticketing.ticket.performance.repository.PerformanceRepository;
import ticketing.ticket.performance.service.PerformanceDetailService;

@Service
public class PerformanceDetailServiceImpl implements PerformanceDetailService{
    private final PerformanceDetailRepository performanceDetailRepository;
    private final PerformanceRepository performanceRepository;

    @Autowired
    public PerformanceDetailServiceImpl(PerformanceDetailRepository performanceDetailRepository, PerformanceRepository performanceRepository){
        this.performanceDetailRepository = performanceDetailRepository;
        this.performanceRepository = performanceRepository;
    }
    // 공연 디테일 저장
    @Override
    public void setPerformanceDetail(PerformanceDetailDto performanceDetailDto) {
        PerformanceDetail performanceDetail = new PerformanceDetail();
        performanceDetail.setArtist(performanceDetailDto.getArtist());
        performanceDetail.setStartTime(performanceDetailDto.getStartTime());
        performanceDetail.setEndTime(performanceDetailDto.getEndTime());
        performanceDetail.setPrice(performanceDetailDto.getPrice());
        performanceDetail.setPerformance(performanceRepository.findById(performanceDetailDto.getPerformanceId()));
        System.out.println(performanceDetail.toString());
        performanceDetailRepository.save(performanceDetail);
        
    }
    // 공연 디테일 단건 조회
    @Override
    public PerformanceDetailDto getPerformanceDetail(Long performanceDetailId) {
        PerformanceDetail performanceDetail = performanceDetailRepository.findById(performanceDetailId);
        PerformanceDetailDto performanceDetailDto = new PerformanceDetailDto();
        performanceDetailDto.setPerformanceDetailId(performanceDetail.getPerformanceDetailId());
        performanceDetailDto.setArtist(performanceDetail.getArtist());
        performanceDetailDto.setStartTime(performanceDetail.getStartTime());
        performanceDetailDto.setEndTime(performanceDetail.getEndTime());
        performanceDetailDto.setPrice(performanceDetail.getPrice());
        performanceDetailDto.setPerformanceId(performanceDetail.getPerformance().getPerformanceId());
        return performanceDetailDto;
    }
    // 공연 디테일 모두 조회
    @Override
    public List<PerformanceDetailDto> getAllPerformanceDetail() {
        List<PerformanceDetail> performanceDetailList = performanceDetailRepository.findAll();
        List<PerformanceDetailDto> dtoList = new ArrayList<>();
        performanceDetailList.forEach(pd->{
            PerformanceDetailDto performanceDetailDto = new PerformanceDetailDto();
            performanceDetailDto.setPerformanceDetailId(pd.getPerformanceDetailId());
            performanceDetailDto.setArtist(pd.getArtist());
            performanceDetailDto.setStartTime(pd.getStartTime());
            performanceDetailDto.setEndTime(pd.getEndTime());
            performanceDetailDto.setPrice(pd.getPrice());
            performanceDetailDto.setPerformanceId(pd.getPerformance().getPerformanceId());
            dtoList.add(performanceDetailDto);
        });
        return dtoList;
    }
    @Override
    public List<PerformanceDetailDto> getPerformanceDetailByPerformanceId(Long PerformanceId) {
      List<PerformanceDetail> performanceDetailList = performanceDetailRepository.findByPerformanceId(PerformanceId);
      List<PerformanceDetailDto> dtoList = new ArrayList<>();
      performanceDetailList.forEach(pd->{
            PerformanceDetailDto performanceDetailDto = new PerformanceDetailDto();
            performanceDetailDto.setPerformanceDetailId(pd.getPerformanceDetailId());
            performanceDetailDto.setArtist(pd.getArtist());
            performanceDetailDto.setStartTime(pd.getStartTime());
            performanceDetailDto.setEndTime(pd.getEndTime());
            performanceDetailDto.setPrice(pd.getPrice());
            performanceDetailDto.setPerformanceId(pd.getPerformance().getPerformanceId());
            dtoList.add(performanceDetailDto);
        });
        return dtoList;
    }

    @Override
    public void deletePerformanceDetail(Long performanceDetailId) {
        performanceDetailRepository.deleteById(performanceDetailId);
    }
    
}
