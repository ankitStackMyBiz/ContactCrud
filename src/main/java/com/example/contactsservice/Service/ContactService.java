package com.example.contactsservice.Service;

import com.example.contactsservice.BadRequest;
import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.NotFound;
import com.example.contactsservice.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public ContactEntity createContact(ContactDTO contactDTO) {
        validateContactDto(contactDTO);
        ContactEntity contact = getContact(contactDTO, new ContactEntity());
        return contactRepository.save(contact);
    }

    public ContactEntity getContact(Long id) {
        ContactEntity contact = contactRepository.getById(id);
        try {
            if (null != contact.getFirstName()) {
                return contact;
            }
            return null;
        } catch (RuntimeException runtimeException) {
            throw new NotFound("User doesn't exist");
        }
    }

    public ContactEntity updateContact(ContactDTO contactDTO, Long id) {
        validateContactDto(contactDTO);
        ContactEntity contact = getContact(id);
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        ContactEntity contact = getContact(id);
        if (null != contact) {
            contactRepository.delete(contact);
        } else {
            throw new NotFound("User doesn't exist");
        }

    }

    private ContactEntity getContact(ContactDTO contactDTO, ContactEntity contact) {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }

    private Boolean validateContactDto(ContactDTO contactDTO) {
        if (contactDTO.getFirstName().length() < 3 || contactDTO.getLastName().length() < 3) {
            throw new BadRequest("Name should be more than 3 characters");
        } else if (String.valueOf(contactDTO.getPhone()).length() < 10) {
            throw new BadRequest("Invalid phone");
        }
        return true;
    }
}
