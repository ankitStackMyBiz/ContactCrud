package com.example.contactsservice.Service;

import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.Exception.NotFound;
import com.example.contactsservice.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    public ContactEntity createContact(ContactDTO contactDTO) {
        ContactEntity contact = getContact(contactDTO, new ContactEntity());
        /*
         * Get contact by phone -
         * if not found create new contact
         * else throw error contact already exist*/
        if (contactRepository.getContactByPhone(contactDTO.getPhone()) == null) {
            return contactRepository.save(contact);
        } else {
            throw new NotFound("Contact already exist with the phone number");
        }
    }

    public List<ContactEntity> getAllContacts(int pageNo, int pageSize, String sortBy) {

        Pageable pageableRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        Page<ContactEntity> allContacts = contactRepository.findAll(pageableRequest);
        if (allContacts.hasContent()) {
            return allContacts.getContent();
        } else {
            return new ArrayList<ContactEntity>();
        }
    }

    public ContactEntity getContact(Long id) {
        ContactEntity contact = contactRepository.getById(id);

        if (null != contact.getFirstName()) {
            return contact;
        } else {
            throw new NotFound("Contact doesn't exist");
        }

    }

    public ContactEntity updateContact(ContactDTO contactDTO, Long id) {
        ContactEntity contact = getContact(id);
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        ContactEntity contact = getContact(id);
        if (contact == null) {
            throw new NotFound("Contact doesn't exist");
        }
        try {
            contactRepository.delete(contact);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to delete contact");
        }
    }

    private ContactEntity getContact(ContactDTO contactDTO, ContactEntity contact) {
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setPhone(contactDTO.getPhone());
        return contact;
    }

}
