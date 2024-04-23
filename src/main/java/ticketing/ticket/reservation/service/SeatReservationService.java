package ticketing.ticket.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.reservation.domain.dto.SeatReservationResponseDto;
import ticketing.ticket.reservation.repository.SeatReservationRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class SeatReservationService {
    private final SeatReservationRepository seatReservationRepository;

    public List<SeatReservationResponseDto> findAll(Long perfDetailId) {
        return seatReservationRepository.findAllByPerfDetailId(perfDetailId);
    }
}
