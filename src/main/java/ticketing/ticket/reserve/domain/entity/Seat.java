package ticketing.ticket.reserve.domain.entity;


import jakarta.persistence.*;
import lombok.Data;
import ticketing.ticket.base.BaseEntity;

@Entity
@Data
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
