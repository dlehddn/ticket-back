package ticketing.ticket.reservation.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaMemSeatReservationRepository implements MemSeatReservationRepository{

    private final EntityManager em;

    @Override
    public void save(MemSeatReservationDto reservationDto) {
        SeatReservation seatReservation = em.find(SeatReservation.class, reservationDto.getSeatReservationId());
        Member member = em.find(Member.class, reservationDto.getMemberId());
        em.persist(MemberSeatReservation.builder()
                .seatReservation(seatReservation)
                .member(member)
                .totalPrice(reservationDto.getTotalPrice())
                .build());
    }
}
