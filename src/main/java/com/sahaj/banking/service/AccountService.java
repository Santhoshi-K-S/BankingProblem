package com.sahaj.banking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sahaj.banking.model.Account;
import com.sahaj.banking.model.User;
import com.sahaj.banking.model.Request.AccountRequest;
import com.sahaj.banking.model.Response.AccountResponse;
import com.sahaj.banking.model.Response.BalanceCheckResponse;
import com.sahaj.banking.repository.AccountRepository;
import com.sahaj.banking.repository.UserRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository repository;
	
	@Autowired
	private UserRepository user_repository;
	
	public AccountResponse createAcc(AccountRequest request) {
		Account account = new Account();
		repository.save(account);
		User user = new User();
		user.setName(request.getName());
		user.setAccountNumber(account.getAccountNumber());
		user_repository.save(user);
		return AccountResponse.builder().message("Your account has been created!").accountNumber(account.getAccountNumber()).build();
	}
	
	public BalanceCheckResponse findBal(int account_number) {
		Account acc = repository.findById(account_number).orElse(null);
		if(acc!=null) {
			return BalanceCheckResponse.builder().balance(acc.getBalance()).build();
		}	
		else {
			return BalanceCheckResponse.builder().balance(-1).build();
		}
	}
}
