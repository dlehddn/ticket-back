package ticketing.ticket.reservation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ticketing.ticket.reservation.domain.dto.BulkReservationDto;
import ticketing.ticket.reservation.domain.dto.SeatReservationResponseDto;
import ticketing.ticket.reservation.domain.entity.QSeatReservation;
import ticketing.ticket.reservation.domain.entity.SeatReservation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ticketing.ticket.reservation.domain.entity.QSeatReservation.seatReservation;

@Repository
@RequiredArgsConstructor
@Slf4j
public class JpaSeatReservationRepository implements SeatReservationRepository {

    private final EntityManager em;
    private final JdbcTemplate jdbcTemplate;
    private final JPAQueryFactory queryFactory;

    @Override
    public void bulkInsert(List<BulkReservationDto> reservations) {
        String sql = "insert into seat_reservation " +
                "(seat_id, performance_detail_id, created_at, updated_at, version, available) " +
                "values(?, ?, ?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setLong(1, reservations.get(i).getSeatId());
                        ps.setLong(2, reservations.get(i).getPerformanceDetailId());
                        ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                        ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                        ps.setLong(5, 0L);
                        ps.setBoolean(6, true);
                    }

                    @Override
                    public int getBatchSize() {
                        return reservations.size();
                    }
                });
    }

    @Override
    public void updateAvailable(Long reserveId) {
        SeatReservation seatReservation = em.find(SeatReservation.class, reserveId);
        seatReservation.setAvailable(false);
        em.flush();
    }

    @Override
    public Optional<SeatReservation> findById(Long id) {
        return Optional.ofNullable(em.find(SeatReservation.class, id));
    }

    @Override
    public List<SeatReservation> findAllByPerfDetailId(Long perfDetailId) {
        return queryFactory
                .select(seatReservation)
                .from(seatReservation)
                .join(seatReservation.seat).fetchJoin()
                .where(equalPerfDetailId(perfDetailId))
                .fetch();
    }

    private BooleanExpression equalPerfDetailId(Long perfDetailId) {
        return seatReservation.performanceDetail
                .performanceDetailId
                .eq(perfDetailId);
    }
}