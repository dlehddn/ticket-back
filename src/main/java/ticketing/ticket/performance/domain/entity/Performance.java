package ticketing.ticket.performance.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import ticketing.ticket.common.entity.BaseEntity;

@Entity
@Data
public class Performance extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceId;

    private String name;

    public Performance(String name) {
        this.name = name;
    }

    public Performance() {
    }
}
