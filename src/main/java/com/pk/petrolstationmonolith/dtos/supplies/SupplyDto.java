package com.pk.petrolstationmonolith.dtos.supplies;

import com.pk.petrolstationmonolith.enums.supplies.FuelType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class SupplyDto {

    @NotNull
    private FuelType fuelType;

    @NotNull
    private LocalDate date;

    @Positive
    private int amount;

    @Positive
    private float price;

    public SupplyDto() {
    }

    public SupplyDto(FuelType fuelType, LocalDate date, int amount, float price) {
        this.fuelType = fuelType;
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
