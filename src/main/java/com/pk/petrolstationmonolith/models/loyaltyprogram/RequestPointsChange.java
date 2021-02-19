package com.pk.petrolstationmonolith.models.loyaltyprogram;

import javax.validation.constraints.Positive;

public class RequestPointsChange {

    @Positive
    private int number;

    public RequestPointsChange() {
    }

    public RequestPointsChange(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
