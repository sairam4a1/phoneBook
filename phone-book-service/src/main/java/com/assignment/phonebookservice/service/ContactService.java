package com.assignment.phonebookservice.service;

import java.util.List;

import com.assignment.phonebookservice.dto.CreateContactDTO;
import com.assignment.phonebookservice.dto.UpdateContactDTO;
import com.assignment.phonebookservice.model.Contact;

public interface ContactService {
	Contact createContact(CreateContactDTO createContactDTO);
	Contact updateContact(UpdateContactDTO updateContactDTO);

	List<Contact> getContactsByPhoneNumber(String phoneNumber);
	List<Contact>getAllContacts();

    String deleteContact(String id);
}
