package com.sahaj.banking;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.sahaj.banking.model.Account;
import com.sahaj.banking.model.Request.AccountRequest;
import com.sahaj.banking.model.Response.AccountResponse;
import com.sahaj.banking.model.Response.BalanceCheckResponse;
import com.sahaj.banking.repository.AccountRepository;
import com.sahaj.banking.repository.UserRepository;
import com.sahaj.banking.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {
		@Autowired
		private AccountService accountService;
		@Mock 
		private AccountRepository accountRepository;
		
		@Mock
		private UserRepository userRepository;
		
		private AccountRequest accountRequest;
		private static final String customerName = "Customer Name";
		
		@Before
		public void init() {
			//MockitoAnnotations.initMocks(this);
			accountService = new AccountService();
		}

		@Test
		void accountCreationTest() {
			accountRequest = new AccountRequest();
			accountRequest.setName(customerName);
			AccountResponse response = accountService.createAcc(accountRequest);
			Assert.assertNotNull(response);
			Assert.assertEquals("Your account has been created!", response.getMessage());
		}
		
		@Test
		void balanceCheckTest() {
			accountRequest = new AccountRequest();
			accountRequest.setName(customerName);
			AccountResponse response = accountService.createAcc(accountRequest);
			BalanceCheckResponse balanceCheckResponse = accountService.findBal(response.getAccountNumber());
			assertEquals(balanceCheckResponse.getBalance(), 0);
			
		}

	
}
