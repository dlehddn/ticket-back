package ticketing.ticket.member.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import ticketing.
        ticket.base.BaseEntity;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Member extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<MemberCoupon> memberCoupons = new ArrayList<>();

    // 발생할 수 있는 문제 생각해보자.
    public void addMemberCoupon(MemberCoupon memberCoupon) {
        this.memberCoupons.add(memberCoupon);
        memberCoupon.setMember(this);
    }

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member() {
    }
}
