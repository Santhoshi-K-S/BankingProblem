package com.sahaj.banking;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Request.AccountRequest;
import com.sahaj.banking.model.Response.AccountResponse;
import com.sahaj.banking.model.Response.TransactionResponse;
import com.sahaj.banking.repository.AccountRepository;
import com.sahaj.banking.repository.UserRepository;
import com.sahaj.banking.service.AccountService;
import com.sahaj.banking.service.DepositService;
import com.sahaj.banking.service.WithdrawService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceTest {
	
	@Autowired 
	private WithdrawService withdrawService;
	
	@Autowired
	private DepositService depositService;
	
	@Autowired
	private AccountService accountService;
	
	
	@Mock
	public AccountRepository accountRepository;
	@Mock 
	public UserRepository userRepository;
	
	private Transaction deposit;
	private Transaction withdraw;
	private AccountRequest accountRequest;
	private AccountResponse accountResponse;
	
	@Before
	public void init() {
		depositService = new DepositService();
		withdrawService = new WithdrawService();
	}
	
	@Test
	public void depositServiceTest() {
		accountRequest = new AccountRequest();
		accountRequest.setName("Name");
		accountResponse = accountService.createAcc(accountRequest);
	    deposit = new Transaction();
		deposit.setAmount(1000);
		deposit.setAccountNumber(accountResponse.getAccountNumber());
		TransactionResponse response = depositService.transact(deposit);
		assertEquals(response.getBalance(), 1000);
	}
	
	@Test
	public void depositAmountInvalid() {
		accountRequest = new AccountRequest();
		accountRequest.setName("Name");
		accountResponse = accountService.createAcc(accountRequest);
		deposit = new Transaction();
		deposit.setAmount(1000000); // amount greater than maximum deposit amount
		deposit.setAccountNumber(accountResponse.getAccountNumber());
		TransactionResponse response = depositService.transact(deposit);
		assertEquals(response.getMessage(), "Please check if the entered deposit amount is valid or you may have exceeded your per day deposit limit");
	}
	
	@Test
	public void withdrawServiceTest() {
		accountRequest = new AccountRequest();
		accountRequest.setName("Name");
		accountResponse = accountService.createAcc(accountRequest);
		Transaction deposit = new Transaction();
		deposit.setAccountNumber(accountResponse.getAccountNumber());
		deposit.setAmount(5000);
		depositService.transact(deposit);
		withdraw = new Transaction();
		withdraw.setAmount(1000);
		withdraw.setAccountNumber(accountResponse.getAccountNumber());
		TransactionResponse response = withdrawService.transact(withdraw);
		assertEquals(response.getBalance(), 4000);
		assertEquals(response.getMessage(), "withdraw is successful!");
	}
	
	@Test
	public void withdrawAmountInvalid() {
		accountRequest = new AccountRequest();
		accountRequest.setName("Name");
		accountResponse = accountService.createAcc(accountRequest);
		Transaction deposit = new Transaction();
		deposit.setAccountNumber(accountResponse.getAccountNumber());
		deposit.setAmount(5000);
		depositService.transact(deposit);
		withdraw = new Transaction();
		withdraw.setAmount(10); // amount lesser than minimum withdrawal amount
		withdraw.setAccountNumber(accountResponse.getAccountNumber());
		TransactionResponse response = withdrawService.transact(withdraw);
		assertEquals(response.getMessage(), "Please check if the entered withdraw amount is valid or you may have exceeded your per day withdraw limit");
	}
}
