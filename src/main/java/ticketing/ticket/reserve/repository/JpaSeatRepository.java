package ticketing.ticket.reserve.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.reserve.domain.dto.SeatSaveDto;
import ticketing.ticket.reserve.domain.entity.Seat;

@Repository
@RequiredArgsConstructor
public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;

    @Override
    public void save(SeatSaveDto seatSaveDto) {
        Seat seat = new Seat(seatSaveDto.getName(), seatSaveDto.getGrade());
        em.persist(seat);
    }

    @Override
    public Seat findById(Long id) {
        return em.find(Seat.class, id);
    }

    @Override
    public void remove(Long id) {
        Seat seat = em.find(Seat.class, id);
        em.remove(seat);
    }
}
