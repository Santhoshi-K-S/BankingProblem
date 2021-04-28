package com.sahaj.banking.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sahaj.banking.model.Request.AccountRequest;
import com.sahaj.banking.model.Response.AccountResponse;
import com.sahaj.banking.model.Response.BalanceCheckResponse;
import com.sahaj.banking.service.AccountService;

@RestController
@RequestMapping("/user")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@PostMapping("/createAccount")
	public AccountResponse createAccount(@RequestBody AccountRequest request) {
		return service.createAcc(request);
	}
	
	@GetMapping("/findBalance/{account_number}")
	public BalanceCheckResponse findBalance(@PathVariable int account_number) {
		return service.findBal(account_number);
	}
	
}
