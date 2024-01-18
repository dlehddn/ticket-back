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
        reserveService.saveReserve(saveDto.getMemberId(), saveDto.getSeatId(), saveDto.getPerfDetailId());
    }

    @GetMapping("/{perfId}")
    public List<ReserveResponseDto> getAllByPerfDetail(@PathVariable Long perfId) {
        return reserveService.findAllByPerfDetail(perfId);
    }
}
