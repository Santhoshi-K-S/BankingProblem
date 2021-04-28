package com.sahaj.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahaj.banking.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
