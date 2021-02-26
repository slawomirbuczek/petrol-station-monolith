package com.pk.petrolstationmonolith.exceptions;

import com.pk.petrolstationmonolith.exceptions.account.*;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationAlreadyTakenException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotFoundException;
import com.pk.petrolstationmonolith.exceptions.carwash.ReservationNotReservedByUserException;
import com.pk.petrolstationmonolith.exceptions.carwash.WrongReservationDateException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.NotEnoughLoyaltyProgramPointsException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.UserHasAlreadyJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.exceptions.loyaltyprogram.UserHasNotJoinedTheLoyaltyProgramException;
import com.pk.petrolstationmonolith.exceptions.supplies.SupplyNotFoundException;
import com.pk.petrolstationmonolith.exceptions.transactions.InvalidTransactionIdException;
import com.pk.petrolstationmonolith.exceptions.transactions.TransactionNotAssociatedWithUserException;
import com.pk.petrolstationmonolith.models.ResponseMessage;
import com.pk.petrolstationmonolith.models.validation.ValidationExceptionMessages;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
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
    @ExceptionHandler(ConstraintViolationException.class)
    public ValidationExceptionMessages handleConstraintViolationException(ConstraintViolationException ex) {

        List<ResponseMessage> errorMessages = new ArrayList<>();

        ex.getConstraintViolations().forEach(violation -> errorMessages.add(
                new ResponseMessage(violation.getMessage()
                )));

        return new ValidationExceptionMessages(errorMessages);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseMessage handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseMessage handleEmailAlreadyTakenException(EmailAlreadyTakenException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseMessage handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseMessage handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IndividualNotFoundException.class)
    public ResponseMessage handleIndividualNotFoundException(IndividualNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationAlreadyTakenException.class)
    public ResponseMessage handleReservationAlreadyTakenException(ReservationAlreadyTakenException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseMessage handleReservationNotFoundException(ReservationNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReservationNotReservedByUserException.class)
    public ResponseMessage handleReservationNotReservedByUserException(ReservationNotReservedByUserException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongReservationDateException.class)
    public ResponseMessage handleWrongReservationDateException(WrongReservationDateException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserHasNotJoinedTheLoyaltyProgramException.class)
    public ResponseMessage handleUserDoesNotParticipateInTheLoyaltyProgramException(UserHasNotJoinedTheLoyaltyProgramException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserHasAlreadyJoinedTheLoyaltyProgramException.class)
    public ResponseMessage handleUserHasAlreadyJoinedTheLoyaltyProgramException(UserHasAlreadyJoinedTheLoyaltyProgramException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEnoughLoyaltyProgramPointsException.class)
    public ResponseMessage handleNotEnoughLoyaltyProgramPointsException(NotEnoughLoyaltyProgramPointsException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTransactionIdException.class)
    public ResponseMessage handleInvalidTransactionIdException(InvalidTransactionIdException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TransactionNotAssociatedWithUserException.class)
    public ResponseMessage handleTransactionNotAssociatedWithUserException(TransactionNotAssociatedWithUserException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SupplyNotFoundException.class)
    public ResponseMessage handleSupplyNotFoundException(SupplyNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

}
