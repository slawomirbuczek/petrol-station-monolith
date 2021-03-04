package com.pk.petrolstationmonolith.dtos.account;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CompanyDto {

    @NotBlank(message = "Company name cannot be blank")
    private String name;

    @NotBlank(message = "Regon cannot be blank")
    private String regon;

    @NotBlank(message = "Nip cannot be blank")
    private String nip;

}
