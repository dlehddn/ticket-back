package ticketing.ticket.reserve.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;
import ticketing.ticket.reserve.domain.dto.ReserveSaveDto;
import ticketing.ticket.reserve.service.ReserveService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reserve")
public class ReserveController {

    private final ReserveService reserveService;

    @PostMapping("/save")
    public void createReserve(@RequestBody ReserveSaveDto saveDto) {
        reserveService.saveReserve(saveDto);
    }

    // 관리자 기능 (공연 디테일 별 예약 현황 조회)
    @GetMapping("/{perfId}")
    public List<ReserveResponseDto> getAllByPerfDetail(@PathVariable Long perfId) {
        return reserveService.findAllByPerfDetail(perfId);
    }

    // 사용자 입장에서 본인 예약 단건 조회 있어야함
}
