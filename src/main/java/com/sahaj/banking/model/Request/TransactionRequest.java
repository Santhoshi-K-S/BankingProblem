package com.sahaj.banking.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {
	private int accountNumber;
	private double amount;
}
