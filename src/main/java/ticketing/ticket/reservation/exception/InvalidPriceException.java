package ticketing.ticket.reservation.exception;

import lombok.Getter;
import ticketing.ticket.common.error.BusinessException;
import ticketing.ticket.common.error.errorcodes.ErrorCode;

@Getter
public class InvalidPriceException extends BusinessException {
    public InvalidPriceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
