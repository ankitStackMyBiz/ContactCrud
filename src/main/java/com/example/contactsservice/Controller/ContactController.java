package com.example.contactsservice.Controller;

import com.example.contactsservice.DTO.ContactDTO;
import com.example.contactsservice.Entity.ContactEntity;
import com.example.contactsservice.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactEntity> create(@Valid @RequestBody ContactDTO contactDTO) {
        return new ResponseEntity<>(contactService.createContact(contactDTO), HttpStatus.CREATED);
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
    public ResponseEntity<ContactEntity> update(@Valid @RequestBody ContactDTO contactDTO, @PathVariable Long id) {
        return new ResponseEntity<>(contactService.updateContact(contactDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        contactService.deleteContact(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAllContacts")
    public ResponseEntity<List<ContactEntity>> getAllContacts(@RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "5") Integer pageSize,
                                                              @RequestParam(defaultValue = "id") String sortBy) {
        List<ContactEntity> list = contactService.getAllContacts(pageNo, pageSize, sortBy);

        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }
}
