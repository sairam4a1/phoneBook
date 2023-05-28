package com.assignment.phonebookservice.controller;

import com.assignment.phonebookservice.dto.CreateContactDTO;
import com.assignment.phonebookservice.dto.UpdateContactDTO;
import com.assignment.phonebookservice.model.Contact;
import com.assignment.phonebookservice.service.ContactService;
import com.assignment.phonebookservice.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contact")
@Slf4j
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponse<Contact>> createContact(@RequestBody @Valid CreateContactDTO createContactDTO) {
        log.info("Calling the contactService to create contact with {}", createContactDTO);
        return ResponseEntity.ok(new ApiResponse<>("Successfully created",
                contactService.createContact(createContactDTO), HttpStatus.CREATED.value()));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Contact>> updateContact(@RequestBody @Valid UpdateContactDTO updateContactDTO) {
        log.info("Calling the contactService to update contact with {}", updateContactDTO);
        return ResponseEntity.ok(new ApiResponse<>("Successfully updated",
                contactService.updateContact(updateContactDTO), HttpStatus.OK.value()));
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<ApiResponse<List<Contact>>> getContactsByPhoneNumber(@PathVariable String phoneNumber) {
        log.info("Calling the contactService to retrieve  contact with phone number {}", phoneNumber);
        return ResponseEntity.ok(new ApiResponse<>("Successfully retrieved contacts",
                contactService.getContactsByPhoneNumber(phoneNumber), HttpStatus.OK.value()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteContact(@PathVariable String id) {
        return ResponseEntity.ok(new ApiResponse<>(contactService.deleteContact(id), "", HttpStatus.OK.value()));

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getAllContacts() {
        log.info("Calling the contactService to retrieve all contact ");
        return ResponseEntity.ok(new ApiResponse<>("Successfully retrieved contacts",
                contactService.getAllContacts(), HttpStatus.OK.value()));
    }
}
