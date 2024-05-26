package ticketing.ticket.reservation.exception;

import lombok.Getter;
import ticketing.ticket.common.error.ErrorCode;

@Getter
public class AlreadyReservationException extends RuntimeException {
    private final ErrorCode errorCode;

    public AlreadyReservationException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }
}
