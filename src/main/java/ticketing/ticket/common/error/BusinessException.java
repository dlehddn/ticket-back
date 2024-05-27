package ticketing.ticket.common.error;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

