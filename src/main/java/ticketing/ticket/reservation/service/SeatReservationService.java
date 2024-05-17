package ticketing.ticket.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.reservation.domain.dto.SeatReservationResponseDto;
import ticketing.ticket.reservation.repository.SeatReservationRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class SeatReservationService {
    private final SeatReservationRepository seatReservationRepository;

    public List<SeatReservationResponseDto> findAll(Long perfDetailId) {
        return seatReservationRepository.findAllByPerfDetailId(perfDetailId)
                .stream()
                .map(s -> SeatReservationResponseDto.builder()
                        .seatReservationId(s.getSeatReservationId())
                        .seat(s.getSeat())
                        .available(s.isAvailable())
                        .build())
                .collect(Collectors.toList());
    }
}
