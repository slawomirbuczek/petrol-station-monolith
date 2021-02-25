package com.pk.petrolstationmonolith.dtos.account;

public class CompanyDto {

    private String name;

    private String regon;

    private String nip;

    public CompanyDto() {
    }

    public CompanyDto(String name, String regon, String nip) {
        this.name = name;
        this.regon = regon;
        this.nip = nip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
