package ticketing.ticket.common.aop.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ticketing.ticket.common.error.ConcurrentErrorCode;
import ticketing.ticket.common.error.ErrorCode;
import ticketing.ticket.common.error.ErrorResponse;
import ticketing.ticket.common.error.SQLErrorCode;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(ObjectOptimisticLockingFailureException e) {
        ErrorCode errorCode = ConcurrentErrorCode.ALREADY_RESERVE_SEAT;
        return makeResponseEntity(errorCode);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e) {
        int errorCode = e.getErrorCode();
        String sqlState = e.getSQLState();
        ErrorCode error;

        // 쿠폰 중복 발급 예외
        if (errorCode == 1062 && sqlState.equals("23000")) {
            error = SQLErrorCode.DUPLICATED_COUPON;
            return makeResponseEntity(error);
        }
        // 동시성 이슈 고려한 쿠폰 수량 부족 예외
        else if (errorCode == 3819 && sqlState.equals("HY000")){
            error = SQLErrorCode.INSUFFICIENT_COUPON;
            return makeResponseEntity(error);
        }
        // 기타 예외
        else {
            error = SQLErrorCode.COMMON_ERROR;
            return makeResponseEntity(error);
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
