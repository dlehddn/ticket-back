package ticketing.ticket.performance.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaPerfDetailRepository implements PerfDetailRepository{

    private final EntityManager em;

    @Override
    public void save(PerfDetailSaveDto saveDto, Long perfId) {
        Performance performance = em.find(Performance.class, perfId);
        PerformanceDetail detail = new PerformanceDetail(performance, saveDto.getArtist(), saveDto.getStartTime(),
                saveDto.getEndTime(), saveDto.getPrice());
        em.persist(detail);
    }

    @Override
    public PerfDetailResponseDto findById(Long perfDetailId) {
        PerformanceDetail find = em.find(PerformanceDetail.class, perfDetailId);
        PerfDetailResponseDto perfResponse = new PerfDetailResponseDto(find.getPerformance().getName(),
                find.getArtist(), find.getStartTime(), find.getEndTime(), find.getPrice());
        return perfResponse;
    }

    @Override
    public List<PerfDetailResponseDto> findAllByPerf(Long perfId) {
        String jpql = "select p from PerformanceDetail p where p.performance.id = :perfId";
        List<PerformanceDetail> tmpList = em.createQuery(jpql, PerformanceDetail.class)
                .setParameter("perfId", perfId)
                .getResultList();
        List<PerfDetailResponseDto> result = tmpList.stream()
                .map(p -> new PerfDetailResponseDto(p))
                .collect(Collectors.toList());
        return result;
    }
}
