package ticketing.ticket.membercoupon.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponSaveDto {
    private Long couponId;
    private Long memberId;
}
