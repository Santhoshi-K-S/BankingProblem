package com.sahaj.banking.service;
import com.sahaj.banking.model.Transaction;
import com.sahaj.banking.model.Response.TransactionResponse;


public interface TransactionService {	
	public TransactionResponse transact(Transaction request);
}
