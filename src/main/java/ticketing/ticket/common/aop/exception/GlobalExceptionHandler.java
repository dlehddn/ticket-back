package ticketing.ticket.common.aop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ticketing.ticket.common.aop.annotation.SlackNotification;
import ticketing.ticket.common.error.*;
import ticketing.ticket.common.error.errorcodes.CommonErrorCode;
import ticketing.ticket.common.error.errorcodes.ConcurrentErrorCode;
import ticketing.ticket.common.error.errorcodes.ErrorCode;
import ticketing.ticket.common.error.errorcodes.SQLErrorCode;
import ticketing.ticket.common.slack.enums.SlackNotificationLevel;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @SlackNotification(level = SlackNotificationLevel.WARN)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        log.warn(e.getErrorCode().getMessage());
        return makeResponseEntity(e.getErrorCode());
    }

    @SlackNotification(level = SlackNotificationLevel.ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCommonException (Exception e) {
        if (e.getMessage() != null) {
            log.error(e.getMessage());
        } else {
            log.error(e.getClass().toString());
            log.error(Arrays.stream(e.getStackTrace())
                    .map(st -> st.toString())
                    .collect(Collectors.joining("\n")));
        }
        return makeResponseEntity(CommonErrorCode.Common_ERROR);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(ObjectOptimisticLockingFailureException e) {
        log.warn("좌석 예약 경합 발생");
        return makeResponseEntity(ConcurrentErrorCode.ALREADY_RESERVE_SEAT);
    }

    @SlackNotification(level = SlackNotificationLevel.WARN)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e) throws SQLException {
        int errorCode = e.getErrorCode();
        String sqlState = e.getSQLState();
        ErrorCode error;

        // 체크 제약 조건 위배
        if (errorCode == 3819 && sqlState.equals("HY000")) {
            error = SQLErrorCode.INSUFFICIENT_COUPON;
            return makeResponseEntity(error);
        } else {
            throw e;
        }
    }

    private ResponseEntity<ErrorResponse> makeResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }
}
