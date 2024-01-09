package ticketing.ticket.reserve.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.performance.repository.PerformanceDetailRepository;
import ticketing.ticket.reserve.domain.dto.ReserveDto;
import ticketing.ticket.reserve.domain.entity.Reserve;
import ticketing.ticket.reserve.repository.ReserveRepository;
import ticketing.ticket.reserve.repository.SeatRepository;
import ticketing.ticket.reserve.service.ReserveService;
@Service
public class ReserveServiceimpl  implements ReserveService{
    private final ReserveRepository reserveRepository;
    private final MemberRepository memberRepository;
    private final SeatRepository seatRepository;
    private final PerformanceDetailRepository performanceDetailRepository;
    @Autowired
    public ReserveServiceimpl(ReserveRepository reserveRepository,
         MemberRepository memberRepository, 
         SeatRepository seatRepository, 
         PerformanceDetailRepository performanceDetailRepository) {
        this.reserveRepository = reserveRepository;
        this.memberRepository = memberRepository;
        this.seatRepository = seatRepository;
        this.performanceDetailRepository = performanceDetailRepository;
    }

    @Override
    public void setReserve(Long memberId, Long seatId, Long performanceDetailId) {
        Reserve reserve = new Reserve();
        reserve.setMember(memberRepository.findById(memberId));
        reserve.setSeat(seatRepository.findbyId(seatId));
        reserve.setPerformanceDetail(performanceDetailRepository.findById(performanceDetailId));
        reserveRepository.save(reserve);
    }

    @Override
    public List<ReserveDto> getReserveByPerformanceDetailId(Long performanceDetailId) {
        List<Reserve> list = reserveRepository.findByPerformanceDetailId(performanceDetailId);
        List<ReserveDto> dtoList  = new ArrayList<>();
        list.forEach(r->{
            ReserveDto reserveDto = new ReserveDto();
            reserveDto.setReserveId(r.getReserveId());
            reserveDto.setMemberName(r.getMember().getName());
            reserveDto.setArtist(r.getPerformanceDetail().getArtist());
            reserveDto.setPrice(r.getPerformanceDetail().getPrice());
            reserveDto.setStartTime(r.getPerformanceDetail().getStartTime());
            reserveDto.setEndTime(r.getPerformanceDetail().getEndTime());
            reserveDto.setSeatName(r.getSeat().getName());
            dtoList.add(reserveDto);
        });
        return dtoList;
    }

    
    
}
