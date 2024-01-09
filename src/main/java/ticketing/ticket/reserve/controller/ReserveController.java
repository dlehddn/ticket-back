package ticketing.ticket.reserve.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import ticketing.ticket.reserve.domain.dto.ReserveDto;
import ticketing.ticket.reserve.service.ReserveService;
import ticketing.ticket.reserve.service.SeatService;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
    private final SeatService seatService;
    private final ReserveService reserveService;
    @Autowired
    public ReserveController(SeatService seatService, ReserveService reserveService){
        this.seatService = seatService;
        this.reserveService = reserveService;
    }
    //좌석 저장 : 유효성검사로 행 개수 제한해야함
    @PostMapping("/set-all-seat")
    public void setAllSeat(@RequestParam int row, @RequestParam int col){
        seatService.setAllSeat(row, col);
    }
    //예약 저장
    @PostMapping("/set-reserve")
    public void setReserve(
        @RequestParam Long memberId,
        @RequestParam Long seatId,
        @RequestParam Long performanceDetailId) {
        reserveService.setReserve(memberId, seatId, performanceDetailId);
    }
    // 공연 디테일별 예약 현황 확인
    @GetMapping("/get-reserve-by-detail/{performanceDetailId}")
    public List<ReserveDto> getReserveListByPerformanceDetailId(@PathVariable Long performanceDetailId){
        return reserveService.getReserveByPerformanceDetailId(performanceDetailId);
        
    }
}
