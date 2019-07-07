package com.zonelynux.peoplefluent;

public interface Ledger {
	void charge(float price, int quantity);
	void applyDiscount(float discount);
	String displayBalance();
}
