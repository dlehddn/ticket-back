package ticketing.ticket.reserve.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import ticketing.ticket.base.BaseEntity;
import ticketing.ticket.member.domain.entity.Member;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;

@Entity
@Data
public class Reserve extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "performance_detail_id")
    private PerformanceDetail performanceDetail;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    public Reserve(Member member, PerformanceDetail performanceDetail, Seat seat) {
        this.member = member;
        this.performanceDetail = performanceDetail;
        this.seat = seat;
    }

    public Reserve() {
    }
}
