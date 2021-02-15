package com.pk.petrolstationmonolith.models.validation;

import com.pk.petrolstationmonolith.models.ResponseMessage;

import java.util.List;

public class ValidationExceptionMessages {

    private List<ResponseMessage> exceptionsMessage;

    public ValidationExceptionMessages() {
    }

    public ValidationExceptionMessages(List<ResponseMessage> exceptionsMessage) {
        this.exceptionsMessage = exceptionsMessage;
    }

    public List<ResponseMessage> getExceptionsMessage() {
        return exceptionsMessage;
    }

    public void setExceptionsMessage(List<ResponseMessage> exceptionsMessage) {
        this.exceptionsMessage = exceptionsMessage;
    }
}
