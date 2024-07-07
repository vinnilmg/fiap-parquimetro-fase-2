package com.fiap.ms_parquimetro_control.config.exception;

import com.fiap.ms_parquimetro_control.exception.CarAlreadyParkedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Slf4j
@RestControllerAdvice
public class ParquimetroControlExceptionHandler {

    @ExceptionHandler({CarAlreadyParkedException.class})
    public ResponseEntity<ApiError> handleBadRequestError(
            final CarAlreadyParkedException e, final WebRequest request
    ) {
        log.info("Erro: {}", e.getMessage());
        final var apiError = makeApiErrorMessage(new ApiErrorMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException e, final WebRequest request
    ) {

        final var errors = e.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(ApiErrorMessage::new)
                .toList();

        final var apiError = makeApiErrorMessage(errors, HttpStatus.UNPROCESSABLE_ENTITY);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleInternalServerError(
            final Exception e, final WebRequest request
    ) {
        log.error("Erro interno: {}", e.getMessage(), e);
        final var apiError = makeApiErrorMessage(new ApiErrorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    private static ApiError makeApiErrorMessage(final List<ApiErrorMessage> errors, final HttpStatus code) {
        final var apiError = new ApiError();
        apiError.setErrors(errors);
        apiError.setStatus(code);
        return apiError;
    }

    private static ApiError makeApiErrorMessage(final ApiErrorMessage error, final HttpStatus code) {
        final var apiError = new ApiError();
        apiError.setErrors(List.of(error));
        apiError.setStatus(code);
        return apiError;
    }
}
