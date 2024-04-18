package ticketing.ticket.seat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.seat.domain.dto.BulkSeatSaveDto;
import ticketing.ticket.seat.domain.entity.Seat;
import ticketing.ticket.seat.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SeatService {
    private final SeatRepository seatRepository;

    public void bulkSeatSave(int rowMax, int colMax) {
        List<BulkSeatSaveDto> seats = new ArrayList<>();
        for (int row = 1; row <= rowMax; row++) {
            for (int col = 1; col <= colMax; col++) {
                String seatName = Character.toString((char) 64 + row) + col;
                seats.add(BulkSeatSaveDto.builder()
                        .name(seatName)
                        .grade(row == 1 ? "VIP" : "NORMAL")
                        .build());
            }
        }
        seatRepository.save(seats);
    }

    public Seat findSeatById(Long id) {
        return seatRepository.findById(id);
    }

    public void removeSeat(Long id) {
        seatRepository.remove(id);
    }
}
