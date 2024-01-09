package ticketing.ticket.reserve.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "performance_detail_id")
    private PerformanceDetail performanceDetail;
}