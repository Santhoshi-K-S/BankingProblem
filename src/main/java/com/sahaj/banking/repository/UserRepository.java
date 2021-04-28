package com.sahaj.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sahaj.banking.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
