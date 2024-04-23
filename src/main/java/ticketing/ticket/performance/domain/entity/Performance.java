package ticketing.ticket.performance.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import ticketing.ticket.common.entity.BaseEntity;

@Entity
@Getter
public class Performance extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceId;

    private String name;

    @Builder
    public Performance(String name) {
        this.name = name;
    }

    public Performance() {
    }
}
