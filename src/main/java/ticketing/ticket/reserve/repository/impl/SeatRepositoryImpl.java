package ticketing.ticket.reserve.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ticketing.ticket.reserve.domain.entity.Seat;
import ticketing.ticket.reserve.repository.SeatRepository;
@Repository
public class SeatRepositoryImpl implements SeatRepository {
    @PersistenceContext
    private  EntityManager em;

    @Override
    @Transactional
    public void save(Seat seat) {
        if (seat.getSeatId() == null) {
            em.persist(seat);
        } else {
            em.merge(seat);
        }
    }

    @Override
    @Transactional
    public void saveAll(List<Seat> list) {
        list.forEach(s->{
            save(s);
        });
    }
    //id 단건 조회
    @Override
    public Seat findbyId(Long SeatId) {
       return em.find(Seat.class, SeatId);
    }

    @Override
    public List<Seat> findAll() {
        return em.createQuery("select s from Seat s",Seat.class).getResultList();
    }
    
}
