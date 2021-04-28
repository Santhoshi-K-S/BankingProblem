package com.sahaj.banking.service;

public enum Check {
	MINIMUMDEPOSITAMOUNT(500),
	MAXIMUMDEPOSITAMOUNT(50000),
	MINIMUMWITHDRAWALAMOUNT(1000),
	MAXIMUMWITHDRAWALAMOUNT(25000),
	MINIMUMBALANCE(0),
	MAXIMUMBALANCE(100000),
	PERDAYLIMIT(3);
    private double checkValue;
  
    Check(double checkValue) {
       this.checkValue = checkValue;
    }
    public double getCheckValue() {
       return this.checkValue;
   }
    

}
