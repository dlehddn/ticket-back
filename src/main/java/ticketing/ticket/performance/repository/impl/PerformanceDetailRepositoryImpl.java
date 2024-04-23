package ticketing.ticket.performance.repository.impl;

import java.util.*;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import ticketing.ticket.performance.domain.dto.PerfSearchDto;
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
    public List<PerformanceDetail> findByPerformanceId(PerfSearchDto perfSearchDto) {
         String jpql = "select p from PerformanceDetail p join fetch p.performance where " +
                "(:perfId is null or p.performance.id = :perfId) and " +
                "(:title is null or p.artist like concat('%', :title, '%')) and " +
                "(:button is null or " +
                "(:button = 'next' and p.id > :index) or " +
                "(:button = 'previous' and p.id < :index)) " +
                "order by case when :button = 'next' then p.id end asc, " +
                "case when :button = 'previous' then p.id end desc";

        TypedQuery<PerformanceDetail> query = em.createQuery(jpql, PerformanceDetail.class);
        if (perfSearchDto.getPerfId() != null) {
            query.setParameter("perfId", perfSearchDto.getPerfId());
        } else {
            query.setParameter("perfId", null);
        }
        if (perfSearchDto.getIndex() != null) {
            query.setParameter("index", perfSearchDto.getIndex());
        } else {
            query.setParameter("index", null);
        }
        if (perfSearchDto.getButton() != null) {
            query.setParameter("button", perfSearchDto.getButton());
        } else {
            query.setParameter("button", null);
        }
        if (perfSearchDto.getTitle() != null) {
            query.setParameter("title", perfSearchDto.getTitle());
        } else {
            query.setParameter("title", null);
        }
        query.setMaxResults(perfSearchDto.getSize());

        List<PerformanceDetail> resultList = query.getResultList();

        return resultList;
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
