package ticketing.ticket.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ticketing.ticket.common.error.PriceErrorCode;
import ticketing.ticket.membercoupon.domain.entity.MemberCoupon;
import ticketing.ticket.membercoupon.repository.MemberCouponRepository;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationDto;
import ticketing.ticket.reservation.domain.dto.MemSeatReservationResponseDto;
import ticketing.ticket.reservation.domain.entity.SeatReservation;
import ticketing.ticket.reservation.exception.InvalidPriceException;
import ticketing.ticket.reservation.repository.MemSeatReservationRepository;
import ticketing.ticket.reservation.repository.SeatReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemSeatReservationService {

    private final MemSeatReservationRepository memSeatReservationRepository;
    private final SeatReservationRepository seatReservationRepository;
    private final MemberCouponRepository memberCouponRepository;

    @Transactional
    public void reserveTicket(MemSeatReservationDto reservationDto) {
        double realTotalPrice = calculateTotalPrice(reservationDto);
        if (!priceIsSame(realTotalPrice, reservationDto.getTotalPrice())) {
            throw new InvalidPriceException(PriceErrorCode.INVALID_PRICE);
        }
        seatReservationRepository.updateAvailable(reservationDto.getSeatReservationId());
        memSeatReservationRepository.save(reservationDto);
    }

    public List<MemSeatReservationResponseDto> getMyReservations(Long memberId) {
        return memSeatReservationRepository.findAllByMemberId(memberId);
    }

    private double calculateTotalPrice(MemSeatReservationDto reservationDto) {
        double noCouponPrice = totalPriceWithNoCoupon(reservationDto);
        return applyCouponAndRemove(noCouponPrice, reservationDto);
    }

    private double totalPriceWithNoCoupon(MemSeatReservationDto reservationDto) {
        SeatReservation seatReservation = seatReservationRepository.findById(reservationDto.getSeatReservationId())
                .orElseThrow();
        int price = seatReservation.getPerformanceDetail().getPrice();
        String grade = seatReservation.getSeat().getGrade();
        return price * (grade.equals("VIP") ? 1.2 : 1.0);
    }

    private double applyCouponAndRemove(double realTotalPrice, MemSeatReservationDto reservationDto) {
        MemberCoupon memberCoupon = null;
        if (reservationDto.getMemberCouponId() != null) {
            memberCoupon = memberCouponRepository.findById(reservationDto.getMemberCouponId())
                    .orElseThrow();
            realTotalPrice *= memberCoupon.getCoupon().getPercent();
            memberCouponRepository.delete(memberCoupon);
        }
        return realTotalPrice;
    }

    private boolean priceIsSame(double realTotalPrice, double clientTotalPrice) {
        return realTotalPrice == clientTotalPrice ? true : false;
    }
}
