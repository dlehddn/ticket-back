package ticketing.ticket.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ticketing.ticket.reservation.domain.dto.SeatReservationResponseDto;
import ticketing.ticket.reservation.service.SeatReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/seat-reservations")
public class SeatReservationController {

    private final SeatReservationService seatReservationService;

    @GetMapping("/{perfDetailId}")
    public ResponseEntity<List<SeatReservationResponseDto>> getSeatAvailable(@PathVariable Long perfDetailId) {
        List<SeatReservationResponseDto> reservations = seatReservationService.findAll(perfDetailId);
        return ResponseEntity.ok(reservations);
    }

}
