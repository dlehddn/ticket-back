package ticketing.ticket.reservation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.performance.domain.entity.QPerformance;
import ticketing.ticket.performance.domain.entity.QPerformanceDetail;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationResponseDto;
import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;
import ticketing.ticket.reservation.domain.entity.QMemberSeatReservation;
import ticketing.ticket.reservation.domain.entity.QSeatReservation;
import ticketing.ticket.reservation.domain.entity.SeatReservation;
import ticketing.ticket.seat.domain.entity.QSeat;

import java.util.List;
import java.util.stream.Collectors;

import static ticketing.ticket.performance.domain.entity.QPerformance.performance;
import static ticketing.ticket.performance.domain.entity.QPerformanceDetail.performanceDetail;
import static ticketing.ticket.reservation.domain.entity.QMemberSeatReservation.memberSeatReservation;
import static ticketing.ticket.reservation.domain.entity.QSeatReservation.seatReservation;
import static ticketing.ticket.seat.domain.entity.QSeat.seat;

@Repository
@Slf4j
@RequiredArgsConstructor
public class JpaMemSeatReservationRepository implements MemSeatReservationRepository{

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

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
    public List<MemberSeatReservation> findAllByMemberId(Long memberId) {
        return queryFactory
                .select(memberSeatReservation)
                .from(memberSeatReservation)
                .join(memberSeatReservation.seatReservation, seatReservation).fetchJoin()
                .join(seatReservation.seat).fetchJoin()
                .join(seatReservation.performanceDetail, performanceDetail).fetchJoin()
                .join(performanceDetail.performance).fetchJoin()
                .where(equalMemberId(memberId))
                .fetch();
    }

    private BooleanExpression equalMemberId(Long memberId) {
        return memberSeatReservation.member
                .memberId
                .eq(memberId);
    }

}
