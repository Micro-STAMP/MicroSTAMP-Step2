package microstamp.step2.configuration;

import microstamp.step2.data.ConnectionType;
import microstamp.step2.data.Style;
import microstamp.step2.exception.Step2Error;
import microstamp.step2.exception.Step2ErrorResponse;
import microstamp.step2.exception.Step2NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Step2ErrorResponse errorResponse = new Step2ErrorResponse();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage;
            String fieldName = ((FieldError) error).getField();

            if(fieldName.equals("nameEqualsEnvironment")) {
                fieldName = "name";
                errorMessage = error.getDefaultMessage();
            }
            else
                errorMessage = fieldName + " is mandatory";

            Step2Error step2Error = new Step2Error(ex.getClass().getSimpleName(), fieldName, errorMessage);
            errorResponse.addError(step2Error);
        });

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
        Step2ErrorResponse errorResponse = new Step2ErrorResponse();
        Throwable cause = ex.getCause();

        if (cause.getMessage().contains(ConnectionType.class.getSimpleName())) {
            errorResponse.addError(new Step2Error(ex.getClass().getSimpleName(), "InvalidEnumValue", "Invalid enum value, valid fields: " + Arrays.toString(ConnectionType.values())));
        } else if (cause.getMessage().contains(Style.class.getSimpleName())){
            errorResponse.addError(new Step2Error(ex.getClass().getSimpleName(), "InvalidEnumValue", "Invalid enum value, valid fields: " + Arrays.toString(Style.values())));
        } else {
            errorResponse.addError(new Step2Error(ex.getClass().getSimpleName(), "HttpMessageNotReadableException",ex.getMessage()));
        }

        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { Step2NotFoundException.class })
    protected ResponseEntity<Object> handleStep2NotFound(Step2NotFoundException ex, WebRequest request) {
        Step2ErrorResponse errorResponse = new Step2ErrorResponse();
        errorResponse.addError(new Step2Error(ex.getClass().getSimpleName(),"NotFound",ex.getMessage()));
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
