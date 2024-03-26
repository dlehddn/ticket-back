package ticketing.ticket.coupon.domain.dto;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CouponResponseDto {

    private String name;
    private Long quantity;
    private Double percent;
    private LocalDate endDate;

    @Builder
    public CouponResponseDto(String name, Long quantity, Double percent, LocalDate endDate) {
        this.name = name;
        this.quantity = quantity;
        this.percent = percent;
        this.endDate = endDate;
    }
}
