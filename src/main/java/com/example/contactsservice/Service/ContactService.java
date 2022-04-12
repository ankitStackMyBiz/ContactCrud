package com.example.contactsservice.Service;

import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    ContactRepository contactRepository;

    public ContactEntity createContact(ContactDTO contactDTO) {
        ContactEntity contact = getContact(contactDTO, new ContactEntity());
        return contactRepository.save(contact);
    }

    public ContactEntity getContact(Long id){
        return contactRepository.getById(id);
    }

    public ContactEntity updateContact(ContactDTO contactDTO, Long id) {
        ContactEntity contact = getContact(id);
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id){

        ContactEntity contact = getContact(id);

        contactRepository.delete(contact);
    }

    private ContactEntity getContact(ContactDTO contactDTO, ContactEntity contact) {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }
}
