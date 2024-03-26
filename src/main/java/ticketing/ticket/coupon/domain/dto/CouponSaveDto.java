package ticketing.ticket.coupon.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CouponSaveDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDate endDate;

    private String name;
    private Long quantity;
    private Double percent;
}
