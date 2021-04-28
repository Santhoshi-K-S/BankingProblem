package com.sahaj.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Request.AccountRequest;
import com.sahaj.banking.model.Request.TransferRequest;
import com.sahaj.banking.model.Response.AccountResponse;
import com.sahaj.banking.model.Response.TransferResponse;
import com.sahaj.banking.repository.AccountRepository;
import com.sahaj.banking.repository.UserRepository;
import com.sahaj.banking.service.AccountService;
import com.sahaj.banking.service.DepositService;
import com.sahaj.banking.service.TransferService;

@SpringBootTest
public class TransferTest {
	@Autowired
	private TransferService transferService;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired 
	private DepositService depositService;
	
	@Mock 
	private AccountRepository accountRepository;
	@Mock 
	private UserRepository userRepository;
	
	private TransferRequest transferRequest;
	private AccountRequest accountRequest;
	private AccountResponse fromAccount;
	private AccountResponse toAccount;
	
	@Before
	public void init() {
		transferService = new TransferService ();
		accountService = new AccountService();
	}
	
	@Test
	
	public void transferTest() {
		accountRequest = new AccountRequest();
		accountRequest.setName("ABC");
		fromAccount = accountService.createAcc(accountRequest);
		accountRequest.setName("XYZ");
		toAccount = accountService.createAcc(accountRequest);
		Transaction deposit = new Transaction();
		deposit.setAccountNumber(fromAccount.getAccountNumber());
		deposit.setAmount(5000);
		depositService.transact(deposit);
		transferRequest = new TransferRequest();
		transferRequest.setAmount(2000);
		transferRequest.setFromAccount(fromAccount.getAccountNumber());
		transferRequest.setToAccount(toAccount.getAccountNumber());
		TransferResponse transferResponse = transferService.transfer(transferRequest);
		assertEquals(transferResponse.getStatus(), "Transfer is successful");
	}
	
	@Test
	public void invalidTransferTest() {
		accountRequest = new AccountRequest();
		accountRequest.setName("ABC1");
		fromAccount = accountService.createAcc(accountRequest);
		accountRequest.setName("XYZ1");
		toAccount = accountService.createAcc(accountRequest);
		Transaction deposit = new Transaction();
		deposit.setAccountNumber(fromAccount.getAccountNumber());
		deposit.setAmount(5000);
		depositService.transact(deposit);
		transferRequest = new TransferRequest();
		transferRequest.setAmount(1000000);
		transferRequest.setFromAccount(fromAccount.getAccountNumber());
		transferRequest.setToAccount(toAccount.getAccountNumber());
		TransferResponse transferResponse = transferService.transfer(transferRequest);
		assertEquals(transferResponse.getStatus(), "Transfer is unsuccessful");
	}
}
