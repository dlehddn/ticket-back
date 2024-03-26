package ticketing.ticket.membercoupon.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Setter;
import ticketing.ticket.base.BaseEntity;
import ticketing.ticket.coupon.domain.entity.Coupon;
import ticketing.ticket.member.domain.entity.Member;

@Entity
@Setter
public class MemberCoupon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberCouponId;

    @OneToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public MemberCoupon() {
    }

    @Builder
    public MemberCoupon(Coupon coupon, Member member) {
        this.coupon = coupon;
        this.member = member;
    }
}
