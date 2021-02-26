package com.pk.petrolstationmonolith.entities.supplies;

import com.pk.petrolstationmonolith.enums.supplies.FuelType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "supply")
public class Supply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    private LocalDate date;

    private int amount;

    private float price;

    public Supply() {
    }

    public Supply(Long id, FuelType fuelType, LocalDate date, int amount, float price) {
        this.id = id;
        this.fuelType = fuelType;
        this.date = date;
        this.amount = amount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
