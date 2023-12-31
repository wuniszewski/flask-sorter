package pl.marcel.flasksorter.adapters.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.marcel.flasksorter.domain.exception.EligibleRackNotFoundException;
import pl.marcel.flasksorter.domain.exception.FlaskAlreadyAssignedException;
import pl.marcel.flasksorter.domain.exception.FlaskNotFoundException;

@ControllerAdvice(basePackages = "pl.marcel.flasksorter.adapters.api")
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = FlaskAlreadyAssignedException.class)
    public ResponseEntity<Object> handleFlaskAlreadyAssignedException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = FlaskNotFoundException.class)
    public ResponseEntity<Object> handleFlaskNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = EligibleRackNotFoundException.class)
    public ResponseEntity<Object> handleEligibleRackNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
