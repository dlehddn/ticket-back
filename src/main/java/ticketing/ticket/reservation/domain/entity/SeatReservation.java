package ticketing.ticket.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import ticketing.ticket.base.BaseEntity;
import ticketing.ticket.performance.domain.entity.PerformanceDetail;
import ticketing.ticket.seat.domain.entity.Seat;

@Entity
@Data
public class SeatReservation extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatReservationId;

    @ManyToOne
    @JoinColumn(name = "performance_detail_id")
    private PerformanceDetail performanceDetail;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private boolean available;

    @Version
    private Long version;

    public SeatReservation() {
    }

    @Builder
    public SeatReservation(PerformanceDetail performanceDetail, Seat seat, boolean available) {
        this.performanceDetail = performanceDetail;
        this.seat = seat;
        this.available = available;
    }
}
