package com.sahaj.banking.model.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AccountResponse {
	private String message;
	private int accountNumber;
}
