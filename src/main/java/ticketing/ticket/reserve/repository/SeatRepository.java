package ticketing.ticket.reserve.repository;

import ticketing.ticket.reserve.domain.entity.Seat;
import java.util.*;
public interface SeatRepository {
    void save(Seat seat);
    void saveAll(List<Seat> list);
    Seat findbyId(Long SeatId);
    List<Seat>findAll();
}
