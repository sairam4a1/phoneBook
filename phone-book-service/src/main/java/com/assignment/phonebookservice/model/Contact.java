package com.assignment.phonebookservice.model;

import java.util.UUID;

import com.assignment.phonebookservice.dto.UpdateContactDTO;
import org.springframework.data.annotation.Id;

import com.assignment.phonebookservice.dto.CreateContactDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Contact {

    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(CreateContactDTO dto) {

        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.phoneNumber = dto.getPhoneNumber();
    }

    public void update(UpdateContactDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.phoneNumber = dto.getPhoneNumber();
    }
}
