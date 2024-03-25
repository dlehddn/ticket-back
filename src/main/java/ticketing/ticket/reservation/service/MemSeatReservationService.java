package ticketing.ticket.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationResponseDto;
import ticketing.ticket.reservation.repository.MemSeatReservationRepository;
import ticketing.ticket.reservation.repository.SeatReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemSeatReservationService {

    private final MemSeatReservationRepository memSeatReservationRepository;
    private final SeatReservationRepository seatReservationRepository;

    public void reserveTicket(MemSeatReservationDto reservationDto) {
        seatReservationRepository.updateAvailable(reservationDto.getSeatReservationId());
        memSeatReservationRepository.save(reservationDto);
    }

    public List<MemSeatReservationResponseDto> getMyReservations(Long memberId) {
        return memSeatReservationRepository.findAllByMemberId(memberId);
    }

}
