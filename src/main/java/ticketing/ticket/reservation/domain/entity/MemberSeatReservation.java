package ticketing.ticket.reservation.domain.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import ticketing.ticket.base.BaseEntity;
import ticketing.ticket.member.domain.entity.Member;


@Entity
@Data
public class MemberSeatReservation extends BaseEntity{

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberSeatReservationId;

    @OneToOne
    @JoinColumn(name = "seat_reservation_id")
    private SeatReservation seatReservation;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer totalPrice;
    
    
}












