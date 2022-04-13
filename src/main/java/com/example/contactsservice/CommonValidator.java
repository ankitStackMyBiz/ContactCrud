package com.example.contactsservice;

import com.example.contactsservice.DTO.ContactDTO;

public class CommonValidator {

    public static Boolean validateContactDto(ContactDTO contactDTO) {
        return contactDTO.getFirstName().length() > 3
                && contactDTO.getLastName().length() > 3
                && String.valueOf(contactDTO.getPhone()).length() == 10;
    }

}
