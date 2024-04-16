package ticketing.ticket.performance.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.domain.dto.PerfSearchDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaPerfDetailRepository implements PerfDetailRepository {

    private final EntityManager em;

    @Override
    public Long save(PerfDetailSaveDto saveDto, Long perfId) {
        Performance performance = em.find(Performance.class, perfId);
        PerformanceDetail detail = new PerformanceDetail(performance, saveDto.getArtist(), saveDto.getStartTime(),
                saveDto.getEndTime(), saveDto.getPrice());
        em.persist(detail);
        return detail.getPerformanceDetailId();
    }

    @Override
    public PerfDetailResponseDto findById(Long perfDetailId) {
        PerformanceDetail find = em.find(PerformanceDetail.class, perfDetailId);
        PerfDetailResponseDto perfResponse = new PerfDetailResponseDto(find.getPerformance().getName(),
                find.getArtist(), find.getStartTime(), find.getEndTime(), find.getPrice());
        return perfResponse;
    }

    @Override
    public List<PerfDetailResponseDto> findAllByPerf(PerfSearchDto perfSearchDto) {
        String jpql = "select p from PerformanceDetail p where " +
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
        List<PerfDetailResponseDto> result = resultList.stream()
                .map(p -> new PerfDetailResponseDto(p))
                .collect(Collectors.toList());
        result.sort(Comparator.comparing(PerfDetailResponseDto::getId));
        return result;
    }
}
