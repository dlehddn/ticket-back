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
import ticketing.ticket.membercoupon.exception.DuplicatedCouponException;

import java.sql.SQLException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleOptimisticLockException(ObjectOptimisticLockingFailureException e) {
        ErrorCode errorCode = ConcurrentErrorCode.ALREADY_RESERVE_SEAT;
        return makeResponseEntity(errorCode);
    }

    @ExceptionHandler(DuplicatedCouponException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedCouponException(DuplicatedCouponException e) {
        ErrorCode errorCode = e.getErrorCode();
        return makeResponseEntity(errorCode);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException e) throws SQLException {
        int errorCode = e.getErrorCode();
        String sqlState = e.getSQLState();
        ErrorCode error;

        // 동시성 이슈 고려한 쿠폰 수량 부족 예외
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
