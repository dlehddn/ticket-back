package ticketing.ticket.performance.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.performance.domain.dto.PerfDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.domain.entity.QPerformance;

import java.util.List;

import static ticketing.ticket.performance.domain.entity.QPerformance.performance;

@Repository
@RequiredArgsConstructor
public class JpaPerfRepository implements PerfRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Override
    public void save(PerfDto perfDto) {
        Performance performance = Performance.builder()
                .name(perfDto.getName())
                .build();
        em.persist(performance);
    }

    @Override
    public Performance findById(Long id) {
        return em.find(Performance.class, id);
    }

    @Override
    public List<Performance> findAll() {
        return queryFactory
                .select(performance)
                .from(performance)
                .fetch();
    }
}
