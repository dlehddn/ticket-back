package ticketing.ticket.common.error.errorcodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode{
    Common_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
