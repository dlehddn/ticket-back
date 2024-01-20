package ticketing.ticket.reserve.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;
import ticketing.ticket.reserve.domain.dto.ReserveResponseDto;
import ticketing.ticket.reserve.domain.dto.ReserveSaveDto;
import ticketing.ticket.reserve.domain.entity.Reserve;
import ticketing.ticket.reserve.domain.entity.Seat;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaReserveRepository implements ReserveRepository {

    private final EntityManager em;

    @Override
    public void save(ReserveSaveDto saveDto) {
        Member member = em.find(Member.class, saveDto.getMemberId());
        Seat seat = em.find(Seat.class, saveDto.getSeatId());
        PerformanceDetail perf = em.find(PerformanceDetail.class, saveDto.getPerfDetailId());
        Reserve reserve = new Reserve(member, perf, seat, saveDto.getTotalPrice());
        em.persist(reserve);
    }

    @Override
    public List<ReserveResponseDto> findAllByDetail(Long perfDetailId) {
        String jpql = "select r from Reserve r join fetch r.performanceDetail join fetch r.seat join fetch r.member where r.performanceDetail.performanceDetailId = :perfDetailId";
        List<Reserve> tmpList = em.createQuery(jpql, Reserve.class)
                .setParameter("perfDetailId", perfDetailId)
                .getResultList();
        List<ReserveResponseDto> result = tmpList.stream()
                .map(r -> ReserveResponseDto.builder()
                        .reserveId(r.getReserveId())
                        .memberId(r.getMember().getMemberId())
                        .seatId(r.getSeat().getSeatId())
                        .perfDetailId(r.getPerformanceDetail().getPerformanceDetailId())
                        .memberName(r.getMember().getName())
                        .seatName(r.getSeat().getName())
                        .artist(r.getPerformanceDetail().getArtist())
                        .startTime(r.getPerformanceDetail().getStartTime())
                        .endTime(r.getPerformanceDetail().getEndTime())
                        .totalPrice(r.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
        return result;
    }
}
