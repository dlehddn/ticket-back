package ticketing.ticket.performance.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import ticketing.ticket.performance.domain.dto.PerfDetailResponseDto;
import ticketing.ticket.performance.domain.dto.PerfDetailSaveDto;
import ticketing.ticket.performance.domain.dto.PerfSearchDto;
import ticketing.ticket.performance.domain.entity.Performance;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;

import java.util.List;
import java.util.Random;

import static ticketing.ticket.performance.domain.entity.QPerformanceDetail.*;

@Repository
@RequiredArgsConstructor
public class JpaPerfDetailRepository implements PerfDetailRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

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
    public List<PerformanceDetail> findAllByPaging(PerfSearchDto perfSearchDto) {
        return queryFactory
                .select(performanceDetail)
                .from(performanceDetail)
                .leftJoin(performanceDetail.performance).fetchJoin()
                .where(equalCategoryId(perfSearchDto.getPerfId()),
                        likeTitleName(perfSearchDto.getTitle()),
                        greaterThanIndex(perfSearchDto.getIndex()))
                .limit(perfSearchDto.getSize())
                .fetch();
    }

    private BooleanExpression likeTitleName(String title) {
        if (StringUtils.hasText(title)) {
            return performanceDetail.artist
                    .like("%" + title + "%");
        }
        return null;
    }

    private BooleanExpression equalCategoryId(Long perfId) {
        if (perfId != null) {
            return performanceDetail.performance
                    .performanceId
                    .eq(perfId);
        }
        return null;
    }

    private BooleanExpression greaterThanIndex(Long perfDetailId) {
        return performanceDetail.performanceDetailId
                .gt(perfDetailId);
    }


}
