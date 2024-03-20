package ticketing.ticket.coupon.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import ticketing.ticket.base.BaseEntity;

import java.time.LocalDate;

@Entity
@Data
public class Coupon extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String name;

    private Double percent;

    private LocalDate endDate;

    private Long quantity;
}
