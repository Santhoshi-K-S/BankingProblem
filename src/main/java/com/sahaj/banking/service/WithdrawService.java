package com.sahaj.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sahaj.banking.model.Account;
import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Response.TransactionResponse;
import com.sahaj.banking.repository.AccountRepository;
import com.sahaj.banking.repository.TransactionRepository;


@Service
public class WithdrawService implements TransactionService{

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	public TransactionResponse transact(Transaction withdraw) {
		String message;
		withdraw.setTransactionType(TransactionType.WITHDRAW.toString());
		Account account = accountRepository.findById(withdraw.getAccountNumber()).orElseThrow();
		if(withdrawPreChecks(withdraw)) {
			transactionRepository.save(withdraw);	
			double balancePostWithdraw=account.getBalance()-withdraw.getAmount();
			account.setBalance(balancePostWithdraw);
			accountRepository.save(account);
			message ="withdraw is successful!";
			
			return TransactionResponse.builder().message(message).accountNumber(withdraw.getAccountNumber()).balance(balancePostWithdraw).build();
		}
		else {
			message = "Please check if the entered withdraw amount is valid or you may have exceeded your per day withdraw limit";
			return TransactionResponse.builder().message(message).accountNumber(withdraw.getAccountNumber()).balance(account.getBalance()).build();
		}
	}
	
	public boolean withdrawPreChecks(Transaction withdraw) {
		CheckWithdrawConditions withdrawConditions = new CheckWithdrawConditions();
		double amount = withdraw.getAmount();
		int accountNumber = withdraw.getAccountNumber();
		Account account = accountRepository.findById(accountNumber).orElseThrow();
		double balance = account.getBalance();
		int count = transactionRepository.count(accountNumber,withdraw.getDate(),TransactionType.WITHDRAW.toString());
		if(withdrawConditions.checkBalanceLimits(amount, balance) && withdrawConditions.checkNumberOfWithdraws(count) && withdrawConditions.checkWithdrawLimits(amount)) {
			return true;
		}
		else {
			return false;
		}
	
	}
	


}
