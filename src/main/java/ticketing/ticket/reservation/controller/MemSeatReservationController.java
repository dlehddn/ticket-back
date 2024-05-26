package ticketing.ticket.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationResponseDto;
import ticketing.ticket.reservation.service.MemSeatReservationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mem-reservations")
public class MemSeatReservationController {

    private final MemSeatReservationService memSeatReservationService;

    @PostMapping
    public ResponseEntity<Void> createTicket(@RequestBody final MemSeatReservationDto reservationDto) {
        memSeatReservationService.reserveTicket(reservationDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<MemSeatReservationResponseDto>> getMyReservations(@PathVariable Long memberId) {
        List<MemSeatReservationResponseDto> reservations = memSeatReservationService.getMyReservations(memberId);
        return ResponseEntity.ok(reservations);
    }
}
