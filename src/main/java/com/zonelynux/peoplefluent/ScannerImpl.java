package com.zonelynux.peoplefluent;

import java.text.NumberFormat;
import java.util.Locale;

public class ScannerImpl implements Scanner {
	
	private Locale locale = Locale.getDefault();
	
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public Leger checkout(ShoppingCart cart) {
		final Leger cl = new LegerImpl();
		cart.forEach(i -> cl.charge(i.getInventoryItem().getPrice(), i.getQuantity()));
		return cl;
	}
	
	public class LegerImpl implements Leger {
		float leger = 0.0f;
		
		@Override
		public void charge(float price, int quantity) {
			leger += price*quantity;
		}
		
		@Override
		public String displayBalance() {
			NumberFormat format = NumberFormat.getCurrencyInstance(locale);
			return format.format(leger);
		}
	}
}
