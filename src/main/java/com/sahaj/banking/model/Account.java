package com.sahaj.banking.model;



import javax.validation.constraints.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
public class Account {
	@Id
	@SequenceGenerator(name="seq",sequenceName="acc_seq",initialValue=1000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private @Getter @Setter int accountNumber;
	@Min(value=0)
	@Max(value=100000)
	private @Getter @Setter double balance=0;

}
