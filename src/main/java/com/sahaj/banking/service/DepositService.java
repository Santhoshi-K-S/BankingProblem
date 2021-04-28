package com.sahaj.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sahaj.banking.model.Account;
import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Response.TransactionResponse;
import com.sahaj.banking.repository.AccountRepository;
import com.sahaj.banking.repository.TransactionRepository;


@Service
public class DepositService implements TransactionService{
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountRepository accountRepository;	
	
	public TransactionResponse transact(Transaction deposit) {
		String message;
		deposit.setTransactionType(TransactionType.DEPOSIT.toString());
		Account account = accountRepository.findById(deposit.getAccountNumber()).orElseThrow();
		if(depositPreChecks(deposit)==true) {
			transactionRepository.save(deposit);	
			double balancePostDeposit=account.getBalance()+deposit.getAmount();
			account.setBalance(balancePostDeposit);
			accountRepository.save(account);
			message ="Deposit is successful!";
			return TransactionResponse.builder().message(message).accountNumber(deposit.getAccountNumber()).balance(balancePostDeposit).build();
		}
		else {
			message = "Please check if the entered deposit amount is valid or you may have exceeded your per day deposit limit";
			return TransactionResponse.builder().message(message).accountNumber(deposit.getAccountNumber()).balance(account.getBalance()).build();
		}
	}
	
	public boolean depositPreChecks(Transaction deposit) {
		CheckDepositConditions depositConditions = new CheckDepositConditions();
		double amount = deposit.getAmount();
		int accountNumber = deposit.getAccountNumber();
		Account account = accountRepository.findById(accountNumber).orElseThrow();
		double balance = account.getBalance();
		int count = transactionRepository.count(accountNumber,deposit.getDate(),TransactionType.DEPOSIT.toString());
		if(depositConditions.checkBalanceLimits(amount, balance) && depositConditions.checkNumberOfDeposits(count) && depositConditions.checkDepositLimits(amount)) {
			return true;
		}
		else {
			return false;
		}

	}
}
