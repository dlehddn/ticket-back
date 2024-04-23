package ticketing.ticket.reservation.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationResponseDto;
import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<MemSeatReservationResponseDto> findAllByMemberId(Long memberId) {
        String jpql = "select r from MemberSeatReservation r " +
                "join fetch r.seatReservation " +
                "join fetch r.seatReservation.seat " +
                "where r.member.id = :memberId";
        List<MemberSeatReservation> resultList = em.createQuery(jpql, MemberSeatReservation.class)
                .setParameter("memberId", memberId)
                .getResultList();
        return resultList.stream()
                .map(r -> MemSeatReservationResponseDto.builder()
                        .seatReservation(r.getSeatReservation())
                        .totalPrice(r.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
