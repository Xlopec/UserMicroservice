package app.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import app.config.gson.ApiError;
import lombok.NonNull;
import lombok.var;

@ControllerAdvice
final class ErrorRestController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        return new ResponseEntity<>(new ApiError(status, findCause(ex)), headers, status);
    }

    private @NonNull Throwable findCause(@NonNull Throwable ex) {
        @NonNull var cause = ex;

        while (cause.getCause() != null) {
            cause = cause.getCause();
        }

        return cause;
    }

}