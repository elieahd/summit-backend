package summit.inbound.rest.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import summit.exceptions.MemberNotFoundByIdException;
import summit.exceptions.MemberWithSameUsernameAlreadyExistsException;
import summit.exceptions.TrackerTemplateNotFoundByIdException;

@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler({
            MemberNotFoundByIdException.class,
            TrackerTemplateNotFoundByIdException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        LOGGER.warn("[404:{}] {}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            MemberWithSameUsernameAlreadyExistsException.class
    })
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        LOGGER.warn("[400:{}] {}", ex.getClass().getSimpleName(), ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }

}
