package com.sahaj.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Request.TransferRequest;
import com.sahaj.banking.model.Response.TransferResponse;


@Service
public class TransferService {
	@Autowired
	DepositService depositService;
	
	@Autowired
	WithdrawService withdrawService;
	
	public TransferResponse transfer(TransferRequest request) {
		Transaction withdraw = new Transaction();
		Transaction deposit = new Transaction();
		withdraw.setAccountNumber(request.getFromAccount());
		deposit.setAccountNumber(request.getToAccount());
		withdraw.setAmount(request.getAmount());
		deposit.setAmount(request.getAmount());
		String message = "";
		if(withdrawService.withdrawPreChecks(withdraw) && depositService.depositPreChecks(deposit)) {
			withdrawService.transact(withdraw);
			depositService.transact(deposit);
			message="Transfer is successful";
			return TransferResponse.builder().status(message).build();
		}
		else {
			message="Transfer is unsuccessful";
			return TransferResponse.builder().status(message).build();
		}
	}
}
