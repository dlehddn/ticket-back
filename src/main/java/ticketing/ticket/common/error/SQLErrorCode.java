package ticketing.ticket.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SQLErrorCode implements ErrorCode{
    INSUFFICIENT_COUPON(HttpStatus.FORBIDDEN, "쿠폰이 소진되었습니다."),
    DUPLICATED_COUPON(HttpStatus.FORBIDDEN, "이미 발급된 쿠폰입니다."),
    RESERVED_SEAT(HttpStatus.FORBIDDEN, "이미 예약된 좌석입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
