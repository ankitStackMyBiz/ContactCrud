package com.example.contactsservice.DTO;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ContactDTO {
    private Long id;

    @NotNull
    @Min(2)
    private String firstName;

    @NotNull
    @Min(2)
    private String lastName;

    @NotNull
    @Min(10)
    @Max(10)
    private long phone;

    public ContactDTO() {

    }
}
