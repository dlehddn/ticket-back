package ticketing.ticket.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.service.MemSeatReservationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mem-seat")
public class MemSeatReservationController {

    private final MemSeatReservationService memSeatReservationService;

    @PostMapping("/reserve")
    public void createTicket(@RequestBody MemSeatReservationDto reservationDto) {
        memSeatReservationService.reserveTicket(reservationDto);
    }
}
