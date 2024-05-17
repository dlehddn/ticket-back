package ticketing.ticket.seat.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ticketing.ticket.common.entity.BaseEntity;

@Entity
@Getter
@Setter
public class Seat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String grade;

    public Seat(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public Seat() {

    }
}
