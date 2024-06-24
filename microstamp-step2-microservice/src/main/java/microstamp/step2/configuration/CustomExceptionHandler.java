package microstamp.step2.configuration;

import microstamp.step2.exception.Step2Error;
import microstamp.step2.exception.Step2ErrorResponse;
import microstamp.step2.exception.Step2NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Step2ErrorResponse errorResponse = new Step2ErrorResponse();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = fieldName + " is mandatory";
            Step2Error step2Error = new Step2Error("MethodArgumentNotValidException", fieldName, errorMessage);
            errorResponse.addError(step2Error);
        });

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Step2NotFoundException.class })
    protected ResponseEntity<Object> handleConflict(Step2NotFoundException ex, WebRequest request) {
        Step2ErrorResponse errorResponse = new Step2ErrorResponse();
        errorResponse.addError(new Step2Error("Step2NotFoundException","NotFound",ex.getMessage()));
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
