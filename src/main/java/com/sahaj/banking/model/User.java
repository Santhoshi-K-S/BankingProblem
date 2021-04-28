package com.sahaj.banking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

@Entity
@Table
public class User {
	@Id
	private @Setter int accountNumber;
	private @Setter String name;

}
