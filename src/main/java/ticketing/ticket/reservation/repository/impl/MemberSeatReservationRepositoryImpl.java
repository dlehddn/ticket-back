package ticketing.ticket.reservation.repository.impl;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;
import ticketing.ticket.reservation.repository.MemberSeatReservationRepository;
@Repository
public class MemberSeatReservationRepositoryImpl implements MemberSeatReservationRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(MemberSeatReservation memberSeatReservation) {
        
        if (memberSeatReservation.getMemberSeatReservationId() == null) {
            em.persist(memberSeatReservation);
        } else {
            em.merge(memberSeatReservation);
        }
        
    }
}
