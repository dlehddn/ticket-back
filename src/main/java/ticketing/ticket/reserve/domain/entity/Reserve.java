package ticketing.ticket.reserve.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reserve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reserveId;

    private Long memberId;
    private Long performanceId;
    private Long seatId;
}
