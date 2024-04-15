package ticketing.ticket.reservation.service.impl;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ticketing.ticket.member.repository.MemberRepository;
import ticketing.ticket.reservation.domain.dto.ReservationRequestDto;
import ticketing.ticket.reservation.domain.entity.MemberSeatReservation;
import ticketing.ticket.reservation.domain.entity.SeatReservation;
import ticketing.ticket.reservation.repository.MemberSeatReservationRepository;
import ticketing.ticket.reservation.repository.SeatReservationRepository;
import ticketing.ticket.reservation.service.ReservationService;
@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final SeatReservationRepository seatReservationRepository;
    private final MemberSeatReservationRepository memberSeatReservationRepository;
    private final MemberRepository memberRepository;

    @Override
    public void setReservation(ReservationRequestDto reservationRequestDto) {
       SeatReservation seatReservation = seatReservationRepository.findById(reservationRequestDto.getSeatReservationId());
       if (seatReservation.isAvailable()) {
           seatReservation.setAvailable(false);
           MemberSeatReservation memberSeatReservation = new MemberSeatReservation();
           memberSeatReservation.setMember( memberRepository.findById( reservationRequestDto.getMemberId() ) );
           memberSeatReservation.setSeatReservation(seatReservation);
           seatReservationRepository.save(seatReservation);
           memberSeatReservationRepository.save(memberSeatReservation);
       } else {
            System.out.println("이미 예약된 좌석입니다.");
       }
    }
}
