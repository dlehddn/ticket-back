package ticketing.ticket.seat.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ticketing.ticket.seat.domain.dto.BulkSeatSaveDto;
import ticketing.ticket.seat.domain.entity.Seat;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(List<BulkSeatSaveDto> seats) {
        String jpql = "insert into seat (name, grade, created_at, updated_at) " +
                "values (?,?,?,?)";
        jdbcTemplate.batchUpdate(jpql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, seats.get(i).getName());
                ps.setString(2, seats.get(i).getGrade());
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            }

            @Override
            public int getBatchSize() {
                return seats.size();
            }
        });
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
