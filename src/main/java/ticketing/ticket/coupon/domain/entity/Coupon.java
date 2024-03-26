package ticketing.ticket.coupon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ticketing.ticket.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Coupon extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String name;

    private Double percent;

    private LocalDate endDate;

    private Long quantity;

    @Builder
    public Coupon(String name, Double percent, LocalDate endDate, Long quantity) {
        this.name = name;
        this.percent = percent;
        this.endDate = endDate;
        this.quantity = quantity;
    }

    public Coupon() {
    }
}
