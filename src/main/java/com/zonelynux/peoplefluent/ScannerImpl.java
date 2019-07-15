package com.zonelynux.peoplefluent;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ScannerImpl implements Scanner {
	
	private Locale locale = Locale.getDefault();
	
	private Map<InventoryItem, Special> specials = new HashMap<>();
	
	private MultiItemSpecial mis;
	
	@Override
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	@Override
	public void addSpecial(Special special) {
		specials.put(special.getInventoryItem(), special);
	}
	
	@Override
	public void setMultiItemSpecial(MultiItemSpecial special) {
		this.mis = special;
	}

	
	@Override
	public Ledger checkout(ShoppingCart cart) {
		final Ledger cl = new LedgerImpl();
		List<ShoppingCart.Item> remaining = new ArrayList<>();
		for (ShoppingCart.Item shoppingCartItem : cart) {
			InventoryItem inventoryItem = shoppingCartItem.getInventoryItem(); 
			int quantity = shoppingCartItem.getQuantity();
			cl.charge(inventoryItem.getPrice(), quantity);
	
			// Now apply any discounts for specials on the item
			Special special = specials.get(inventoryItem);
			if (special != null) {
				Discount d = special.computeDiscount(quantity);
				cl.applyDiscount(d.getDiscountedPrice());
				remaining.add(new ItemImpl(shoppingCartItem.getInventoryItem(), d.getNonDiscountedItems()));
			}
			
		}
//		if (mis != null) {
//			cl.applyDiscount(mis.computeDiscount(remaining));
//		}
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
