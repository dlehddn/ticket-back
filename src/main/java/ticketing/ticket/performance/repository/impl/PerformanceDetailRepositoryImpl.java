package ticketing.ticket.performance.repository.impl;

import java.util.*;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;
import ticketing.ticket.performance.repository.PerformanceDetailRepository;

@Repository
public class PerformanceDetailRepositoryImpl implements PerformanceDetailRepository {

    @PersistenceContext
    private  EntityManager em;
    // 공연 디테일 저장, 업데이트
    @Override
    public void save(PerformanceDetail performanceDetail) {
        if (performanceDetail.getPerformanceDetailId() == null) {
            em.persist(performanceDetail);
        } else {
            em.merge(performanceDetail);
        }
    }
    // id 단건 조회
    @Override
    public PerformanceDetail findById(Long performanceDetailId) {
        return em.find(PerformanceDetail.class, performanceDetailId);
    }
    // 카테고리별 조회
    @Override
    public List<PerformanceDetail> findByPerformanceId(Long PerformanceId) {
        TypedQuery<PerformanceDetail> query = em.createQuery("select pd from PerformanceDetail pd where pd.performance.performanceId = :PerformanceId", PerformanceDetail.class);
        query.setParameter("PerformanceId", PerformanceId);
        List<PerformanceDetail> list = new ArrayList<>();
        list = query.getResultList();

        return list;
    }
   
    // 모두 조회
    @Override
    public List<PerformanceDetail> findAll() {
        return em.createQuery("select p from PerformanceDetail p", PerformanceDetail.class).getResultList();
    }

    //id 단건 삭제
    @Override
    public void deleteById(Long performanceDetailId) {
        PerformanceDetail performanceDetail = findById(performanceDetailId);
        if (performanceDetail != null) {
            em.remove(performanceDetail);
        }
    }
    
    
}
