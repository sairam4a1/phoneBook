package com.assignment.phonebookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateContactDTO {
	@NotEmpty(message = "id should not null or empty")
	private String id;
	@NotEmpty(message = "firstName should not null or empty")
	private String firstName;
	@NotEmpty(message = "lastName should not null or empty")
	private String lastName;
	@NotEmpty(message = "phoneNumber should not null or empty")
	private String phoneNumber;
}
