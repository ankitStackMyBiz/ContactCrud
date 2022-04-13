package com.example.contactsservice.Service;

import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.Exception.NotFound;
import com.example.contactsservice.Repository.ContactRepository;
import javafx.beans.binding.When;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
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
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertEquals("Test", contactEntity.getFirstName());
    }

    @Test
    @DisplayName("Should fail when empty/null firstName is provided")
    void createContactEmptyFirstName(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("");
        contactDTO.setLastName("Test_");
        contactDTO.setPhone(1234567890);
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertNotNull(contactEntity);
    }

    @Test
    @DisplayName("Should fail when less than 3 characters in firstName is provided")
    void createContactFirstNameCharacterCount(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("aa");
        contactDTO.setLastName("Test_");
        contactDTO.setPhone(1234567890);
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertNotNull(contactEntity);
    }

    @Test
    @DisplayName("Should fail when empty/null LastName is provided")
    void createContactEmptyLastName(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("");
        contactDTO.setLastName("");
        contactDTO.setPhone(1234567890);
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertNotNull(contactEntity);
    }

    @Test
    @DisplayName("Should fail when less than 3 characters in LastName is provided")
    void createContactLastNameCharacterCount(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("aaaaaa");
        contactDTO.setLastName("Te");
        contactDTO.setPhone(1234567890);
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertNotNull(contactEntity);
    }


    @Test
    @DisplayName("Should fail when less than 10 characters in Phone is provided")
    void createContactEmptyPhone(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("Abnki");
        contactDTO.setLastName("Kumar");
        contactDTO.setPhone(9864);
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertNotNull(contactEntity);
    }

    @Test
    @DisplayName("Should fail when greater than 10 characters in Phone is provided")
    void createContactPhoneCharacterCount(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("aaaaaa");
        contactDTO.setLastName("Test");
        contactDTO.setPhone(1234567897);
        when(repository.getContactByPhone(anyLong())).thenReturn(null);
        when(repository.save(any())).then(returnsFirstArg());
        ContactEntity contactEntity = contactService.createContact(contactDTO);
        assertNotNull(contactEntity);
    }

    @Test
    @DisplayName("Should throw error when creating existing contact")
    void createContactExisting(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("aaaaaa");
        contactDTO.setLastName("Test");
        contactDTO.setPhone(1234567890);

        ContactEntity existingContact = new ContactEntity();
        existingContact.setPhone(1234567890L);
        when(repository.getContactByPhone(anyLong())).thenReturn(existingContact);
        assertThrows(NotFound.class, () -> contactService.createContact(contactDTO));
    }

    @Test
    @DisplayName("Should pass when reading contact by id")
    void readContact(){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setFirstName("aaaaaa");
        contactDTO.setLastName("Test");
        contactDTO.setPhone(1234567897);
        when(repository.getById(1L)).thenReturn(getContact(contactDTO, new ContactEntity()));
        ContactEntity contactEntity = contactService.getContact(1L);
        assertEquals("Test",contactEntity.getLastName());
        assertNotNull(contactEntity);
    }

    private ContactEntity getContact(ContactDTO contactDTO, ContactEntity contact) {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }

}