package ticketing.ticket.reservation.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import ticketing.ticket.common.entity.BaseEntity;
import ticketing.ticket.member.domain.entity.Member;

@Entity
@Data
public class MemberSeatReservation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeatReservationId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "seat_reservation_id")
    private SeatReservation seatReservation;

    private Integer totalPrice;

    public MemberSeatReservation() {
    }

    @Builder
    public MemberSeatReservation(Member member, SeatReservation seatReservation, Integer totalPrice) {
        this.member = member;
        this.seatReservation = seatReservation;
        this.totalPrice = totalPrice;
    }
}
