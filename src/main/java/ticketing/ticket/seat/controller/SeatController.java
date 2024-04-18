package ticketing.ticket.seat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.seat.domain.dto.BulkSeatSaveDto;
import ticketing.ticket.seat.domain.dto.SeatSaveRequest;
import ticketing.ticket.seat.service.SeatService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @PostMapping("/save")
    public void createSeat(@RequestBody SeatSaveRequest dto) {
        seatService.bulkSeatSave(dto.getRow(), dto.getCol());
    }

//    @GetMapping("/{seatId}")
//    public Seat getSeat(@PathVariable Long seatId) {
//        return seatService.findSeatById(seatId);
//    }
//
//    @DeleteMapping("/{seatId}")
//    public void deleteSeat(@PathVariable Long seatId) {
//        seatService.removeSeat(seatId);
//    }
}
