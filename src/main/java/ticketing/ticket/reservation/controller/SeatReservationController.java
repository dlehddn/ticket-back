package ticketing.ticket.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ticketing.ticket.reservation.service.SeatReservationService;

@RestController
@RequiredArgsConstructor
public class SeatReservationController {

    private final SeatReservationService seatReservationService;

}
