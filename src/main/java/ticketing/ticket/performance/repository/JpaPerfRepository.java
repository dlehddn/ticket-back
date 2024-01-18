package ticketing.ticket.performance.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.performance.domain.dto.PerfDto;
import ticketing.ticket.performance.domain.entity.Performance;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaPerfRepository implements PerfRepository {

    private final EntityManager em;

    @Override
    public void save(PerfDto perfDto) {
        Performance performance = new Performance(perfDto.getName());
        em.persist(performance);
    }

    @Override
    public Performance findById(Long id) {
        return em.find(Performance.class, id);
    }

    @Override
    public List<Performance> findAll() {
        String jpql = "select p from Performance p";
        List<Performance> result = em.createQuery(jpql, Performance.class)
                .getResultList();
        return result;
    }
}
