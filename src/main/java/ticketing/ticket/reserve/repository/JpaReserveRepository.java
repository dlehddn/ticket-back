package ticketing.ticket.reserve.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;
import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;
import ticketing.ticket.reserve.domain.entity.Reserve;
import ticketing.ticket.reserve.domain.entity.Seat;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaReserveRepository implements ReserveRepository{

    private final EntityManager em;

    @Override
    public void save(Long memberId, Long seatId, Long perfDetailId) {
        Member member = em.find(Member.class, memberId);
        Seat seat = em.find(Seat.class, seatId);
        PerformanceDetail perf = em.find(PerformanceDetail.class, perfDetailId);
        Reserve reserve = new Reserve(member, perf, seat);
        em.persist(reserve);
    }

    @Override
    public List<ReserveResponseDto> findAllByDetail(Long perfDetailId) {
        String jpql = "select r from Reserve r where r.performanceDetail.performanceDetailId = :perfDetailId";
        List<Reserve> tmpList = em.createQuery(jpql, Reserve.class)
                .setParameter("perfDetailId", perfDetailId)
                .getResultList();
        List<ReserveResponseDto> result = tmpList.stream()
                .map(r -> new ReserveResponseDto(r.getMember().getMemberId(), r.getMember().getName(),
                        r.getSeat().getName(),
                        r.getPerformanceDetail().getPerformanceDetailId(),
                        r.getPerformanceDetail().getArtist()))
                .collect(Collectors.toList());
        return result;
    }
}
