package ticketing.ticket.member.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import ticketing.ticket.base.BaseEntity;
import ticketing.ticket.coupon.domain.entity.Coupon;

@Entity
@Data
public class MemberCoupon extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCouponId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

}
