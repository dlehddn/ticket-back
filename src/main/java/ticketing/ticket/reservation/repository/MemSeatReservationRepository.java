package ticketing.ticket.reservation.repository;

import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationResponseDto;
import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;

import java.util.List;

public interface MemSeatReservationRepository {

    void save(MemSeatReservationDto reservationDto);

    List<MemSeatReservationResponseDto> findAllByMemberId(Long memberId);
}
