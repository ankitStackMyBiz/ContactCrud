package com.example.contactsservice.Repository;

import com.example.contactsservice.Entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    ContactEntity getContactByPhone(Long phone);
}
