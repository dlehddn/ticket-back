package ticketing.ticket.reservation.repository;

import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;

public interface MemberSeatReservationRepository {
    void save(MemberSeatReservation memberSeatReservation);
}
