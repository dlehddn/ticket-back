package ticketing.ticket.common.jwt;

import ticketing.ticket.common.error.BusinessException;
import ticketing.ticket.common.error.errorcodes.ErrorCode;

public class TokenNotValidException extends BusinessException {
    public TokenNotValidException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode);
    }
}
