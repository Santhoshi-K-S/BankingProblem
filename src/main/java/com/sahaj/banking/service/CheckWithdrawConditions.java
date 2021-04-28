package com.sahaj.banking.service;

public class CheckWithdrawConditions {
	public boolean checkWithdrawLimits(double amount) {
		if(amount>= Check.MINIMUMWITHDRAWALAMOUNT.getCheckValue() && amount<=Check.MAXIMUMWITHDRAWALAMOUNT.getCheckValue()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkBalanceLimits(double amount, double balance) {
		if(amount <= balance) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean checkNumberOfWithdraws(int withdrawCount) {
		if(withdrawCount<Check.PERDAYLIMIT.getCheckValue()) {
			return true;
		}
		else {
			return false;
		}
	}

}
