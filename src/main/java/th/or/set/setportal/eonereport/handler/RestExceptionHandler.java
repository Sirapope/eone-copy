package th.or.set.setportal.eonereport.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import th.or.set.setportal.eonereport.bean.response.ErrorResponse;
import th.or.set.setportal.eonereport.exception.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UnAuthorizedToReportException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedToReportException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse("unauthorized to report"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = UnAuthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnAuthorizedException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest request, Throwable e) {
        log.error("NotFoundException : {} / {}", e.getStackTrace()[0].toString(), LocalDateTime.now());
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(HttpServletRequest request, Throwable e) {
        FieldError fieldError = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError();
        log.error("field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage());
        return new ResponseEntity<>(new ErrorResponse("field '" + fieldError.getField() + "' " + fieldError.getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ReportAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleReportAlreadyExistsException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Throwable e) {
        log.error("Exception : {}", e.getMessage());
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointerException(HttpServletRequest request, Throwable e) {
        log.error("NullPointerException : {} / {}", e.getStackTrace()[0].toString(), LocalDateTime.now());
        return new ResponseEntity<>(new ErrorResponse(e.getStackTrace()[0].toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<?> handleAsyncRequestTimeoutException(HttpServletRequest request, Throwable ex) {
        log.error("error current-user");
        return new ResponseEntity<>("timeout", HttpStatus.OK);
    }

    @ExceptionHandler(value = ExternalException.class)
    public ResponseEntity<ErrorResponse> handleExternalException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ResourceLockedException.class)
    public ResponseEntity<ErrorResponse> handleResourceLockedException(HttpServletRequest request, Throwable e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.LOCKED);
    }

    @ExceptionHandler(value = InformationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInformationNotFoundException(HttpServletRequest request, InformationNotFoundException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = MultipleAccessException.class)
    public ResponseEntity<ErrorResponse> handleMultipleAccessException(HttpServletRequest request, MultipleAccessException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.CONFLICT);
    }
}
