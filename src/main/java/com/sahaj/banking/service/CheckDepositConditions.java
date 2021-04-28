package com.sahaj.banking.service;

public class CheckDepositConditions {
	
	public boolean checkDepositLimits(double amount) {
		if(amount>= Check.MINIMUMDEPOSITAMOUNT.getCheckValue() && amount<=Check.MAXIMUMDEPOSITAMOUNT.getCheckValue()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkBalanceLimits(double amount, double balance) {
		if(amount+balance<=Check.MAXIMUMBALANCE.getCheckValue()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkNumberOfDeposits(int depositCount) {
		if(depositCount<Check.PERDAYLIMIT.getCheckValue()) {
			return true;
		}
		else {
			return false;
		}
	}
}
