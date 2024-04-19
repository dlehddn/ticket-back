package ticketing.ticket.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PriceErrorCode implements ErrorCode {

    INVALID_PRICE(HttpStatus.FORBIDDEN, "계산 가격이 틀립니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
