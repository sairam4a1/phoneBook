package com.assignment.phonebookservice.service;

import com.assignment.phonebookservice.dto.CreateContactDTO;
import com.assignment.phonebookservice.dto.UpdateContactDTO;
import com.assignment.phonebookservice.exception.ContactNotFoundException;
import com.assignment.phonebookservice.model.Contact;
import com.assignment.phonebookservice.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository repository;

    @Override
    public Contact createContact(CreateContactDTO createContactDTO) {
        Contact contact = new Contact(createContactDTO);
        log.info("Successfully created contact with {}", contact);
        return repository.save(contact);
    }

    @Override
    public Contact updateContact(UpdateContactDTO updateContactDTO) {
        Optional<Contact> byId = repository.findById(updateContactDTO.getId());
        if (byId.isPresent()) {
            Contact contact = byId.get();
            contact.update(updateContactDTO);
            Contact savedContact = repository.save(contact);
            log.info("Successfully updated contact with {}", savedContact);
            return savedContact;
        }
        throw new ContactNotFoundException("Contact not found with id: " + updateContactDTO.getId());
    }

    @Override
    public List<Contact> getContactsByPhoneNumber(String phoneNumber) {
        List<Contact> allByPhoneNumber = repository.findAllByPhoneNumber(phoneNumber);
        log.info("Successfully retrieved contacts based phone number count: {}", (long) allByPhoneNumber.size());
        return allByPhoneNumber;
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contactList = repository.findAll();
        log.info("Successfully retrieved all contacts count: {}", (long) contactList.size());
        return contactList;
    }

    @Override
    public String deleteContact(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            throw new ContactNotFoundException("Unable to delete requested contact");
        }
        return "Successfully deleted contact";
    }

}
