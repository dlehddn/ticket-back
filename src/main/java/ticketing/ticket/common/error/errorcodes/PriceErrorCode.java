package ticketing.ticket.common.error.errorcodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import ticketing.ticket.common.error.errorcodes.ErrorCode;

@Getter
@RequiredArgsConstructor
public enum PriceErrorCode implements ErrorCode {

    INVALID_PRICE(HttpStatus.FORBIDDEN, "계산 가격이 틀립니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
