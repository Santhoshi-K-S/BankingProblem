package com.sahaj.banking.model;

import java.sql.Date;
import java.util.Calendar;

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
public class Transaction{
	@Id
	@SequenceGenerator(name="seq1",sequenceName="dep_seq",initialValue=10000,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq1")
	private int transactionId;
	@Getter Date date= new Date(Calendar.getInstance().getTime().getTime());
	private @Getter @Setter double amount;
	private @Getter @Setter int accountNumber;
	private @Setter String transactionType;
}
