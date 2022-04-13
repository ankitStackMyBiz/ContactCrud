package com.example.contactsservice.Service;

import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.Repository.ContactRepository;
import javafx.beans.binding.When;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ContactServiceTest {

    @Mock
    ContactRepository repository;

    @InjectMocks
    ContactService contactService;

    @Test
    @DisplayName("Should create contact")
    void createContact(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("Test");
        contactDTO.setLastName("Test_Last_Name");
        contactDTO.setPhone(1234567890);

        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertEquals("Test", contactEntity.getFirstName());
    }
}