package ticketing.ticket.membercoupon.exception;

import ticketing.ticket.common.error.BusinessException;
import ticketing.ticket.common.error.errorcodes.ErrorCode;

public class InsufficientCouponException extends BusinessException {
    public InsufficientCouponException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }
}
