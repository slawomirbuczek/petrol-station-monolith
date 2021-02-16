package com.pk.petrolstationmonolith.exceptions;

import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.validation.ValidationExceptionMessages;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ValidationExceptionMessages handleValidationExceptions(BindException ex) {

        List<ResponseMessage> errorMessages = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> errorMessages.add(
                new ResponseMessage(error.getDefaultMessage()
                )));

        return new ValidationExceptionMessages(errorMessages);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseMessage handleEmailNotFoundException(EmailNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEmailTokenException.class)
    public ResponseMessage handleInvalidEmailTokenException(InvalidEmailTokenException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseMessage handleInvalidPasswordException(InvalidPasswordException ex) {
        return new ResponseMessage(ex.getMessage());
    }


}
