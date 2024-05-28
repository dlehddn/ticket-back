package ticketing.ticket.common.aop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ticketing.ticket.common.aop.annotation.SlackAlarm;
import ticketing.ticket.common.error.*;
import ticketing.ticket.common.error.errorcodes.ConcurrentErrorCode;
import ticketing.ticket.common.error.errorcodes.ErrorCode;
import ticketing.ticket.common.error.errorcodes.SQLErrorCode;
import ticketing.ticket.common.slack.enums.SlackAlarmLevel;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @SlackAlarm(level = SlackAlarmLevel.ERROR)
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        return makeResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(ObjectOptimisticLockingFailureException e) {
        ErrorCode errorCode = ConcurrentErrorCode.ALREADY_RESERVE_SEAT;
        return makeResponseEntity(errorCode);
    }

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
