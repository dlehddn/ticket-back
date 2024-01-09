package ticketing.ticket.performance.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;
import ticketing.ticket.base.BaseEntity;
import java.util.*;


@Entity
@Data
public class Performance extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long performanceId;

    private String name;

    @OneToMany(mappedBy = "performance" , cascade = CascadeType.MERGE)
    @ToString.Exclude
    private List<PerformanceDetail> performanceDetailList = new ArrayList<>();
}