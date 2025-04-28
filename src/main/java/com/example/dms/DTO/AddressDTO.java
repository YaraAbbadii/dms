package com.example.dms.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDTO {

    private Long id;
    @NotNull
    @NotBlank
    private String street;
    @NotNull
    @NotBlank
    private String city;
    private String zipCode;
}
