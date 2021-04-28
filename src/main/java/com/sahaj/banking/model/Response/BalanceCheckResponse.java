package com.sahaj.banking.model.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BalanceCheckResponse{
	private double balance;
}
