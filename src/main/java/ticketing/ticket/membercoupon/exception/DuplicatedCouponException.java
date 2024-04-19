package ticketing.ticket.membercoupon.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ticketing.ticket.common.error.ErrorCode;

@Getter
public class DuplicatedCouponException extends RuntimeException{
    private final ErrorCode errorCode;

    public DuplicatedCouponException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
