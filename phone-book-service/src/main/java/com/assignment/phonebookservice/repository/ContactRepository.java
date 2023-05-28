package com.assignment.phonebookservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.assignment.phonebookservice.model.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String>  {

    List<Contact>findAllByPhoneNumber(String phoneNumber);

}
