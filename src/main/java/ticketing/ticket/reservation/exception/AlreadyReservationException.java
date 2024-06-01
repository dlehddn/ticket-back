package ticketing.ticket.reservation.exception;

import lombok.Getter;
import ticketing.ticket.common.error.BusinessException;
import ticketing.ticket.common.error.errorcodes.ErrorCode;

@Getter
public class AlreadyReservationException extends BusinessException {
    public AlreadyReservationException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }
}
