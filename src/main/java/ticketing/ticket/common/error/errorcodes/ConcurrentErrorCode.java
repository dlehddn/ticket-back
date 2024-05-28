package ticketing.ticket.common.error.errorcodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ticketing.ticket.common.error.errorcodes.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum ConcurrentErrorCode implements ErrorCode {
    ALREADY_RESERVE_SEAT(HttpStatus.FORBIDDEN, "이미 예약된 좌석입니다."),
    NO_COUPON(HttpStatus.FORBIDDEN, "쿠폰이 소진되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
