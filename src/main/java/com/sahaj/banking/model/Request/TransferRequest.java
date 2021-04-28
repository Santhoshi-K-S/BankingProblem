package com.sahaj.banking.model.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {
	private int fromAccount;
	private int toAccount;
	private double amount;
}
