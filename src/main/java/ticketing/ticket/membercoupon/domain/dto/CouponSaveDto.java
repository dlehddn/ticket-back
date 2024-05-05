package ticketing.ticket.membercoupon.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CouponSaveDto {
    private Long couponId;
    private Long memberId;
}
