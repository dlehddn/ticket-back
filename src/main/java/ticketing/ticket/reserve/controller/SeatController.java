package ticketing.ticket.reserve.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.reserve.domain.dto.SeatSaveDto;
import ticketing.ticket.reserve.domain.entity.Seat;
import ticketing.ticket.reserve.service.SeatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/save")
    public void createSeat(@RequestBody SeatSaveDto seatSaveDto) {
        seatService.saveSeat(seatSaveDto);
    }

    @GetMapping("/{seatId}")
    public Seat getSeat(@PathVariable Long seatId) {
        return seatService.findSeatById(seatId);
    }

    @DeleteMapping("/{seatId}")
    public void deleteSeat(@PathVariable Long seatId) {
        seatService.removeSeat(seatId);
    }
}
