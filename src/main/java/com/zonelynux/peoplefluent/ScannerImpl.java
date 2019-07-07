package com.zonelynux.peoplefluent;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ScannerImpl implements Scanner {
	
	private Locale locale = Locale.getDefault();
	
	private Map<InventoryItem, Special> specials = new HashMap<>();
	
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public void addSpecial(Special special) {
		specials.put(special.getInventoryItem(), special);
	}
	
	@Override
	public Ledger checkout(ShoppingCart cart) {
		final Ledger cl = new LedgerImpl();
		for (ShoppingCart.Item shoppingCartItem : cart) {
			InventoryItem inventoryItem = shoppingCartItem.getInventoryItem(); 
			int quantity = shoppingCartItem.getQuantity();
			cl.charge(inventoryItem.getPrice(), quantity);
	
			// Now apply any discounts for specials on the item
			Special special = specials.get(inventoryItem);
			if (special != null) {
				cl.applyDiscount(special.computeDiscount(quantity));
			}
		}
		return cl;
	}
	
	public class LedgerImpl implements Ledger {
		float balance = 0.0f;
		
		@Override
		public void charge(float price, int quantity) {
			balance += price*quantity;
		}
		
		@Override
		public void applyDiscount(float discount) {
			balance -= discount;
		}
		
		@Override
		public String displayBalance() {
			NumberFormat format = NumberFormat.getCurrencyInstance(locale);
			return format.format(balance > 0.0 ? balance : 0.0);
		}
	}
}
