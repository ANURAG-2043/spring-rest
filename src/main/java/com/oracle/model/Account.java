package com.oracle.model;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Component
@Scope(scopeName = "prototype")
@Primary
@Entity
@Table(name="myaccount")
public class Account {
	@Id
	@Column(name="acc_id")
	private int accountId;
	
	@Column(name="balance")
	private int balance;

	public Account() {
		System.out.println("Account object created.");
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
}