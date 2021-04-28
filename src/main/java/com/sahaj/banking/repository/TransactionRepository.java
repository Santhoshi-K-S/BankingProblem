package com.sahaj.banking.repository;
import java.sql.Date;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sahaj.banking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

	@Query(value="SELECT count(*) from transaction where account_number = ?1 AND date = ?2 AND transaction_type = ?3", nativeQuery=true)
	int count(int accountNumber,Date date,String transactionType);
}
