package com.sahaj.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Request.TransactionRequest;
import com.sahaj.banking.model.Request.TransferRequest;
import com.sahaj.banking.model.Response.TransactionResponse;
import com.sahaj.banking.model.Response.TransferResponse;
import com.sahaj.banking.service.DepositService;
import com.sahaj.banking.service.TransferService;
import com.sahaj.banking.service.WithdrawService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class TransactionController {
	@Autowired
	private WithdrawService withdrawService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private TransferService transferService;
	
	@PostMapping("/deposit")
	public TransactionResponse deposit(@RequestBody TransactionRequest request) {
		Transaction deposit = new Transaction();
		deposit.setAccountNumber(request.getAccountNumber());
		deposit.setAmount(request.getAmount());
		return depositService.transact(deposit);
	}
	
	@PostMapping("/withdraw")
	public TransactionResponse withdraw(@RequestBody TransactionRequest request) {
		Transaction withdraw = new Transaction();
		withdraw.setAccountNumber(request.getAccountNumber());
		withdraw.setAmount(request.getAmount());
		return withdrawService.transact(withdraw);
	}
	
	@PostMapping("/transfer")
	public TransferResponse transfer(@RequestBody TransferRequest request) {
		return transferService.transfer(request);
	}
	
}
	
