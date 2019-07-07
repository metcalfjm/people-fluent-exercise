package com.zonelynux.peoplefluent;

public interface Ledger {
	void charge(float price, int quantity);
	String displayBalance();
}
