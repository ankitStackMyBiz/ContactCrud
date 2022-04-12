package com.example.contactsservice.DTO;

import lombok.Data;

@Data
public class ContactDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private long phone;

}
