package ticketing.ticket.reserve.repository.impl;

import org.springframework.stereotype.Repository;
import java.util.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import ticketing.ticket.reserve.domain.entity.Reserve;
import ticketing.ticket.reserve.repository.ReserveRepository;
@Repository
public class ReserveRepositoryImpl implements ReserveRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void save(Reserve reserve) {
        em.persist(reserve);
    }

    @Override
    public List<Reserve> findByPerformanceDetailId(Long performanceDetailId) {
       return em.createQuery("select r from Reserve r join fetch r.performanceDetail join fetch r.seat join fetch r.member where r.performanceDetail.performanceDetailId = :performanceDetailId",Reserve.class)
        .setParameter("performanceDetailId", performanceDetailId)
        .getResultList();

    }

    
    
}
