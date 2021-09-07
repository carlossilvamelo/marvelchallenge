package com.marvelchallenge.exception.config;

import com.marvelchallenge.exception.ExceptionCodes;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler({StandarRuntimeException.class})
    public ResponseEntity<StandardError> exceptionsHandler(StandarRuntimeException e, HttpServletRequest request) {

        log.error(String.format("Request '%s' threw the exception %s", request.getRequestURI(), e.toString()));
        HttpStatus status = e.getStatus();
        StandardError standardError = new StandardError(LocalDateTime.now().format(FORMATTER), status.value(),
                e.getStatus().name(), e.getMessage(), request.getRequestURI(), e.getExceptionCode());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler({FeignException.class})
    public ResponseEntity<StandardError> exceptionsFeignExceptionHandler(FeignException e, HttpServletRequest request) {
        log.error(String.format("Request '%s' threw the exception %s", request.getRequestURI(), e.toString()));
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError standardError = new StandardError(LocalDateTime.now().format(FORMATTER), status.value(),
                status.name(), e.getMessage(), request.getRequestURI(), ExceptionCodes.HTTP_CLIENT_ERROR);
        return ResponseEntity.status(status).body(standardError);
    }

}