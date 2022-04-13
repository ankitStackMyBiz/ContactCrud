package com.example.contactsservice.Controller;


import com.example.contactsservice.BadRequest;
import com.example.contactsservice.CommonValidator;
import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactEntity> create(@RequestBody ContactDTO contactDTO) {
        if (CommonValidator.validateContactDto(contactDTO)) {
            return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);
        } else {
            throw new BadRequest("Invalid request");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> read(@PathVariable Long id) {
        ContactDTO contactDTO = new ContactDTO();
        ContactEntity contactEntity = contactService.getContact(id);
        contactDTO.setId(contactEntity.getId());
        contactDTO.setFirstName(contactEntity.getFirstName());
        contactDTO.setLastName(contactEntity.getLastName());
        contactDTO.setPhone(contactEntity.getPhone());
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactEntity> update(@RequestBody ContactDTO contactDTO, @PathVariable Long id) {
        if (CommonValidator.validateContactDto(contactDTO)) {
            return new ResponseEntity<>(contactService.updateContact(contactDTO, id), HttpStatus.OK);
        } else {
            throw new BadRequest("Invalid request");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
