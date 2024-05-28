package ticketing.ticket.common.error.errorcodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode{
    TOKEN_NOT_VALID(HttpStatus.FORBIDDEN, "유효하지 않은 토큰입니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
