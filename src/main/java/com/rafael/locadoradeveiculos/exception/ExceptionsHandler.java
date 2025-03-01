package com.rafael.locadoradeveiculos.exception;

import com.rafael.locadoradeveiculos.exception.messages.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionsHandler {


    @ExceptionHandler({ValidacaoException.class})
    public ResponseEntity<ErrorMessage> methodValidacaoException(ValidacaoException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body( new ErrorMessage(
                        HttpStatus.BAD_REQUEST.value(),
                        exception.getMessage()
                ));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorMessage> methodException(Exception exception) {

        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body( new ErrorMessage(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        exception.getMessage()
                ));
    }
}
