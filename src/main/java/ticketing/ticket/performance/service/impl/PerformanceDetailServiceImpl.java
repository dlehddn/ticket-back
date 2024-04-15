package ticketing.ticket.performance.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ticketing.ticket.performance.domain.dto.PerformanceDetailDto;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;
import ticketing.ticket.performance.repository.PerformanceDetailRepository;
import ticketing.ticket.performance.repository.PerformanceRepository;
import ticketing.ticket.performance.service.PerformanceDetailService;
import ticketing.ticket.reservation.domain.entity.SeatReservation;
import ticketing.ticket.reservation.repository.SeatReservationRepository;
import ticketing.ticket.seat.domain.entity.Seat;
import ticketing.ticket.seat.repository.SeatRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class PerformanceDetailServiceImpl implements PerformanceDetailService{
    private final PerformanceDetailRepository performanceDetailRepository;
    private final PerformanceRepository performanceRepository;
    private final SeatReservationRepository seatReservationRepository;
    private final SeatRepository seatRepository;
   
    // 공연 디테일 저장
    @Override
    public void setPerformanceDetail(PerformanceDetailDto performanceDetailDto) {
        PerformanceDetail performanceDetail = new PerformanceDetail();
        performanceDetail.setArtist(performanceDetailDto.getArtist());
        performanceDetail.setStartTime(performanceDetailDto.getStartTime());
        performanceDetail.setEndTime(performanceDetailDto.getEndTime());
        performanceDetail.setPrice(performanceDetailDto.getPrice());
        performanceDetail.setPerformance(performanceRepository.findById(performanceDetailDto.getPerformanceId()));
        
        performanceDetailRepository.save(performanceDetail);
        // SeatReservation 벌크 인서트
        List<Seat> sList = seatRepository.findAll();
        List<SeatReservation> srList = new ArrayList<>();
        sList.forEach(s->{
            SeatReservation  seatReservation = new SeatReservation();
            seatReservation.setPerformanceDetail(performanceDetail);
            seatReservation.setSeat(s);
            srList.add(seatReservation);
        });
        seatReservationRepository.bulkInsert(srList);


        
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
