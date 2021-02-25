package com.pk.petrolstationmonolith.dtos.account;

public class IndividualDto {

    private String firstName;

    private String lastName;

    private String pesel;

    private String nip;

    public IndividualDto() {
    }

    public IndividualDto(String firstName, String lastName, String pesel, String nip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.nip = nip;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
