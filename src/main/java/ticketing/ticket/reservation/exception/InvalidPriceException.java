package ticketing.ticket.reservation.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ticketing.ticket.common.error.ErrorCode;

@Getter
@RequiredArgsConstructor
public class InvalidPriceException extends RuntimeException{
    private final ErrorCode errorCode;
}
