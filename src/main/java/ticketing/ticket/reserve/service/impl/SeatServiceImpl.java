package ticketing.ticket.reserve.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ticketing.ticket.reserve.domain.entity.Seat;
import ticketing.ticket.reserve.repository.SeatRepository;
import ticketing.ticket.reserve.service.SeatService;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository){
        this.seatRepository = seatRepository;
    }

    @Override
    public void setAllSeat(int row, int col) {
        List<Seat> list = new ArrayList<>();
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                Seat seat = new Seat();
                int r = 64+i;
                int c = j;
                char  cr = (char)r;
                String seatName = Character.toString(cr) + String.valueOf(j);
                seat.setName(seatName);
                list.add(seat);
            }
        }
        seatRepository.saveAll(list);
    }
    
}
